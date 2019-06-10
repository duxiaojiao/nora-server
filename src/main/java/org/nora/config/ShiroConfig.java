package org.nora.config;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.nora.modules.shiro.UserRealm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public Realm realm() {
        return new UserRealm();
    }

    /**
     * 这里统一做鉴权，即判断哪些请求路径需要用户登录，哪些请求路径不需要用户登录。
     * 这里只做鉴权，不做权限控制，因为权限用注解来做。
     * @return
     */
//    @Bean
//    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
//        DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();
//        //哪些请求可以匿名访问
//        chain.addPathDefinition("/system/account/login", "anon");
//        chain.addPathDefinition("/system/account/logout", "anon");
//        chain.addPathDefinition("/system/account/401", "anon");
//        chain.addPathDefinition("/system/account/403", "anon");
//        chain.addPathDefinition("/system/account/index", "anon");
//
//        //除了以上的请求外，其它请求都需要登录
//        chain.addPathDefinition("/**", "authc");
//        return chain;
//    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/system/account/login", "anon"); //登录接口排除
        filterChainDefinitionMap.put("/system/account/logout", "anon");//登录验证码
        filterChainDefinitionMap.put("/system/account/401", "anon");
        filterChainDefinitionMap.put("/system/account/index", "anon");
        filterChainDefinitionMap.put("/system/account/403", "anon");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/system/account/401");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/system/account/index");
        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/system/account/403");
        filterChainDefinitionMap.put("/**", "authc");//登录验证码
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

//    @Bean("securityManager")
//    public DefaultWebSecurityManager securityManager(UserRealm myRealm) {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(myRealm);
//        return securityManager;
//    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        return securityManager;
    }


}
