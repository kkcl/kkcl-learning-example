package com.kkcl.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

//public class ShiroRealm extends AuthenticatingRealm {
public class ShiroRealm extends AuthorizingRealm {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        /**
         System.out.println("====================");
         System.out.println("doGetAuthenticationInfo:"+token.hashCode());
         System.out.println("====================");
         return null;
         **/
        System.out.println("[ShiroRealm] doGetAuthenticationInfo:"+token.hashCode());

        UsernamePasswordToken   upToken = (UsernamePasswordToken)token;
        String username = upToken.getUsername();

        //调用数据库的方法，从数据中查询username对应的用户记录
        System.out.println("从数据库中共获取到username:"+username +"所对应的用户信息");

        if("unknow".equals(username)){
            throw new UnknownAccountException("用户不存在！");
        }
        if("monster".equals(username)){
            throw new LockedAccountException("该用户已被锁定！");
        }

        //principal认证的实体的相关的信息，可以是username,也可以是数据库表对应的用户的实体类的对象
        Object principal = username;
        //凭证(密码)
        Object credentials = null;
        if("admin".equals(username)){
            credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
        }else if("user".equals(username)){
            credentials = "098d2c478e9c11555ce2823231e02ec1";
        }
        //当前realm的名称
        String realmName = getName();
        //盐值加密
        ByteSource byteSourceSalt  = ByteSource.Util.bytes(username);

        SimpleAuthenticationInfo info = null;

        info = new SimpleAuthenticationInfo(principal,credentials,byteSourceSalt,realmName);
        return info;
    }

    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        Object credentials = "123456";
        Object salt = ByteSource.Util.bytes("admin");
        int hashIterator = 1024;

        Object result = new SimpleHash(hashAlgorithmName,credentials,salt,hashIterator);
        System.out.println(result);
    }

    //演示授权的方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Object principal = principals.getPrimaryPrincipal();

        //利用登陆用户的信息来取得登陆用户的角色
        Set<String> roles = new HashSet<String>();
        roles.add("user");
        if("admin".equals(principal)){
            roles.add("admin");
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        return info;
    }
}
