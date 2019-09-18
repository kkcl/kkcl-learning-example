package com.kkcl.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.ByteSource;

public class SecondRealm extends AuthenticatingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        /**
         System.out.println("====================");
         System.out.println("doGetAuthenticationInfo:"+token.hashCode());
         System.out.println("====================");
         return null;
         **/
        System.out.println("[SecondRealm]  doGetAuthenticationInfo:"+token.hashCode());

        UsernamePasswordToken upToken = (UsernamePasswordToken)token;
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
        Object credentials = "123456";
        if("admin".equals(username)){
            credentials = "0d414ed7ade260fc506cbf4628041b4c17fe8f32";
        }else if("user".equals(username)){
            credentials = "073d4c3ae812935f23cb3f2a71943f49e082a718";
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
        Object credentials = "admin";
        Object salt = ByteSource.Util.bytes("123456");
        int hashIterator = 1024;

        Object result = new SimpleHash(hashAlgorithmName,credentials,salt,hashIterator);
        System.out.println(result);
    }
}
