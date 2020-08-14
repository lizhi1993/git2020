package com.lanxin.config;

import com.lanxin.unit.Dome2;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public  Dome2 dome2(HashedCredentialsMatcher hashedCredentialsMatcher){
        Dome2 d=new Dome2();
        d.setCredentialsMatcher(hashedCredentialsMatcher);
        return d;
    }

    @Bean
    public SecurityManager securityManager(Dome2 dome2,RedisCacheManager redisCacheManager,DefaultWebSessionManager defaultWebSessionManager){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();

        securityManager.setRealm(dome2);
        securityManager.setCacheManager(redisCacheManager);
        securityManager.setSessionManager(defaultWebSessionManager);
        return securityManager;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){

        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(5);
        return hashedCredentialsMatcher;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String,String> filter=new LinkedHashMap<String, String>();
        filter.put("/login","anon");
        filter.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filter);
        shiroFilterFactoryBean.setLoginUrl("/unauth");
        return shiroFilterFactoryBean;

    }

    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);

        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor attributeSourceAdvisor=new AuthorizationAttributeSourceAdvisor();
        attributeSourceAdvisor.setSecurityManager(securityManager);

        return attributeSourceAdvisor;
    }

    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager=new RedisManager();
        return redisManager;

    }

    @Bean
    public RedisCacheManager cacheManager(RedisManager redisManager){
        RedisCacheManager redisCacheManager=new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);

        return  redisCacheManager;
    }


    @Bean
    public DefaultWebSessionManager defaultWebSessionManager(RedisSessionDAO redisSessionDAO){
        DefaultWebSessionManager defaultWebSessionManager=new DefaultWebSessionManager();
        defaultWebSessionManager.setSessionDAO(redisSessionDAO);
        return defaultWebSessionManager;
    }

    @Bean
    public RedisSessionDAO redisSessionDAO(RedisManager redisManager){
        RedisSessionDAO redisSessionDAO=new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager);
        return redisSessionDAO;
    }

}
