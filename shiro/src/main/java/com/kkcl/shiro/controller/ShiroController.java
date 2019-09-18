package com.kkcl.shiro.controller;

import com.kkcl.shiro.service.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shiro")
public class ShiroController {

    @Autowired
    private ShiroService shiroService;

    @RequestMapping("/ testShiroAnnoation")
    public String testShiroAnnoation(ShiroHttpSession session){
        shiroService.testMethod();
        session.setAttribute("key","val123");
        return "redirect:/list.jsp";

    }

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password){
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            token.setRememberMe(true);
            try {
                System.out.println("====================");
                System.out.println("1."+token.hashCode());
                subject.login(token);
            } catch (AuthenticationException e) {
                System.out.println("登陆失败："+ e.getMessage());
            }
        }
        return "redirect:/list.jsp";
    }
}
