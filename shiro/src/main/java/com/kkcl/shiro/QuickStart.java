package com.kkcl.shiro;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;


public class QuickStart {
    private static  final org.apache.log4j.Logger logger = Logger.getLogger(QuickStart.class);
    public static void main(String[] args) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject =  SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("key","shiro value");
        String v = (String) session.getAttribute("key");
        if(v.equals("shiro value")){
            logger.info("---> this value is ["+ v +"]");
        }

        if(!subject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr","vespa");
            token.setRememberMe(true);
            try {
                subject.login(token);
            }catch (UnknownAccountException e) {
                logger.info("--->there is no user with name of"+ token.getPrincipal());
                return;
            }catch (IncorrectCredentialsException e) {
                logger.info("--->password for account "+ token.getPrincipal()+"was incorrect!");
                return;
            }catch (LockedAccountException e) {
                logger.info("--->the account for username"+ token.getPrincipal() + "is locked");
                return;
            }catch (AuthenticationException e) {
                logger.info("authentication error!");
                return;
            }
        }
        logger.info("--->user ["+subject.getPrincipal()+"] login in successfully");

        //验证是否含有指定的角色
        if(subject.hasRole("shcsd")){
            logger.info("---> has the role of shcsd");
        }else{
            logger.info("---> has no the role of shcsd");
        }

        //验证是否含有特定的权限
        if(subject.isPermitted("lightsaber:weild")){
            logger.info("---> you have the permit of lightsaber:weild ");
        }else{
            logger.info("---> you do not have the permit of lightsaber:weild ");
        }

        //针对特定角色的权限
        if(subject.isPermitted("user:delete:zhangsan")){
            logger.info("--->you have the permit of user:delete:zhangsan");
        }else{
            logger.info("--->you do not have the permit of user:delete:zhangsan");
        }

        //登出系统
        System.out.println("---->"+subject.isAuthenticated());
        subject.logout();
        System.out.println("---->"+subject.isAuthenticated());
        System.exit(0);
    }
}
