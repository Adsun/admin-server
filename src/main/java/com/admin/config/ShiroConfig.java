package com.admin.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fengxiang
 * @date 2018-10-22
 */
@Configuration
public class ShiroConfig {
	@Autowired
	private MyShiroRealm myShiroRealm;

	@Bean
	public SecurityManager securityManager() {
		
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		
		/*HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName("SHA1");
		credentialsMatcher.setHashIterations(2);
		credentialsMatcher.setStoredCredentialsHexEncoded(true);
		myShiroRealm.setCredentialsMatcher(credentialsMatcher);*/
		securityManager.setRealm(myShiroRealm);
		CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
		Cookie cookie = new SimpleCookie(CookieRememberMeManager.DEFAULT_REMEMBER_ME_COOKIE_NAME);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(7 * 24 * 60 * 60 + 8 * 60 * 60);
        rememberMeManager.setCookie(cookie);
		securityManager.setRememberMeManager(rememberMeManager);
		return securityManager;
	}
	
	@Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.getFilters().put("authentication", new AuthenticationFilter());
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");
        filterChainDefinitionMap.put("/web/**", "anon");
        
        filterChainDefinitionMap.put("/**", "authentication");
        
        shiroFilterFactoryBean.setLoginUrl("/unauth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        
        return shiroFilterFactoryBean;
        
    }
}
