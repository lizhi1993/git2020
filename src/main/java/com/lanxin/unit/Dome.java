package com.lanxin.unit;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

public class Dome {
    public static void main(String[] args) {
//        SimpleAccountRealm simpleAccountRealm=new SimpleAccountRealm();
//
//        simpleAccountRealm.addAccount("lz","123456","admin","user");

        Dome2 dome2=new Dome2();

        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();

        defaultSecurityManager.setRealm(dome2);

        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(5);
        dome2.setCredentialsMatcher(hashedCredentialsMatcher);

        SecurityUtils.setSecurityManager(defaultSecurityManager);

        Subject subject=SecurityUtils.getSubject();

        UsernamePasswordToken token=new UsernamePasswordToken("admin","123");

        subject.login(token);

        System.out.println(subject.isAuthenticated());

        subject.checkRole("超管");

        subject.checkPermissions("select","update");



    }


}
