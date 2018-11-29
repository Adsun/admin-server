package com.admin.config;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * @author fengxiang
 * @date 2018-10-23
 */
@Configuration
public class WebMvcApp implements WebMvcConfigurer {
	@Autowired
	private InterceptorConfig interceptorConfig;
	
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", buildConfig());
		return new CorsFilter(source);
	}

	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		// 允许跨域访问的域名
		corsConfiguration.addAllowedOrigin("http://localhost:18007");
		// 请求头
		corsConfiguration.addAllowedHeader("*");
		// 请求方法
		corsConfiguration.addAllowedMethod("*");
		// 预检请求的有效期，单位为秒。
		corsConfiguration.setMaxAge(3600L);
		// 是否支持安全证书
		corsConfiguration.setAllowCredentials(true);
	
		return corsConfiguration;
	}
	
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptorConfig)
        //添加需要验证登录用户操作权限的请求
        .addPathPatterns("/**")
        //排除不需要验证登录用户操作权限的请求
        .excludePathPatterns("/login","/logout","/error","/unauth","/sendMsg","/checkAuth","/addFile/**","/contact/**","/cooper/**",
        		"/swagger-ui.html","/webjars/**","/swagger-resources/**","/v2/**","/supplier/constant","/supplier/area","/project/user");
	}
	
	@Bean
	@Autowired
	public JPAQueryFactory queryFactory(EntityManager entityManager) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		return queryFactory;
	} 
	

}
