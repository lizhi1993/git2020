package com.lanxin.unit;

import com.lanxin.dao.EmpDao;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

public class Dome2  extends AuthorizingRealm {

    @Autowired
    private EmpDao empDao;

    //封装授权信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String ename=principalCollection.getPrimaryPrincipal().toString();

//            EmpDao mapper = SqlSessionFacory.getSqlSession().getMapper(EmpDao.class);
            Set<String> strings = empDao.selectRname(ename);
            Set<String> strings1 = empDao.selectQname(ename);
            SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.setRoles(strings);
            simpleAuthorizationInfo.setStringPermissions(strings1);
            return simpleAuthorizationInfo;

    }

    //封装认证信息

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String ename=authenticationToken.getPrincipal().toString();


//            EmpDao  mapper = SqlSessionFacory.getSqlSession().getMapper(EmpDao.class);
            String password = empDao.selectEmpName(ename);

            if(password!=null){

                SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(ename,password,"customRealm");
                simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("lizhi"+ename));
                return simpleAuthenticationInfo;

            }
        return null;

    }




}
