package com.ms.config.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Ming
 */
@Configuration
@EnableConfigurationProperties(ShiroProperties.class)
public class ShiroConfig {

    @Resource
    private ShiroProperties shiroProperties;

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.database}")
    private int database;


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager,ShiroProperties shiroProperties) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //身份认证失败，则跳转到登录页面的配置 没有登录的用户请求需要登录的页面时自动跳转到登录页面，不是必须的属性，不输入地址的话会自动寻找项目web项目的根目录下的”/login.jsp”页面。
        shiroFilterFactoryBean.setLoginUrl(shiroProperties.getLoginUrl());
        //登录成功默认跳转页面，不配置则跳转至”/”。如果登陆前点击的一个需要登录的页面，则在登录自动跳转到那个需要登录的页面。不跳转到此。
        shiroFilterFactoryBean.setSuccessUrl(shiroProperties.getSuccessUrl());
        //没有权限默认跳转的页面
//        shiroFilterFactoryBean.setUnauthorizedUrl(shiroProperties.getUnauthorizedUrl());
        //自定义拦截器
//        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
//        shiroFilterFactoryBean.setFilters(filtersMap);
        // 权限控制map.
        Map<String, String> filterMap = new LinkedHashMap<>();
        String[] anons = shiroProperties.getAnonUrl().split(",");
        for (String url : anons){
            filterMap.put(url, "anon");
        }
        filterMap.put("/api/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }


    @Bean
    public SecurityManager securityManager(UpmsRealm upmsRealm,RedisCacheManager cacheManager,SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(upmsRealm);
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager);
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    /**
     * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
     *
     * @return
     */
    @Bean
    public UpmsRealm upmsRealm() {
        return new UpmsRealm();
    }

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;

    }

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        String redisHost = host + ":" + port;
        redisManager.setHost(redisHost);
        redisManager.setPassword(password);
        redisManager.setDatabase(database);
        redisManager.setTimeout(timeout);
        return redisManager;
    }

    /**
     * Session Manager
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public SessionManager sessionManager(ShiroSessionListener sessionListener) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        sessionManager.setDeleteInvalidSessions(true);
        Collection<SessionListener> listeners = new ArrayList<>();
        listeners.add(sessionListener);
        sessionManager.setSessionListeners(listeners);
        return sessionManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * 配置session监听
     * @return
     */
    @Bean("sessionListener")
    public ShiroSessionListener sessionListener(){
        return new ShiroSessionListener();
    }


    /***
     * 授权所用配置
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /***
     * 使授权注解起作用不如不想配置可以在pom文件中加入
     * <dependency>
     *<groupId>org.springframework.boot</groupId>
     *<artifactId>spring-boot-starter-aop</artifactId>
     *</dependency>
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


}
