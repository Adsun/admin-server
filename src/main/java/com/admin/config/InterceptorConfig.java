package com.admin.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.admin.constant.ResultConstant;
import com.alibaba.fastjson.JSON;

/**
 * 拦截器进行权限认证
 * @author fengxiang
 * @date 2018-10-23
 */
@Configuration
public class InterceptorConfig implements HandlerInterceptor {

	private static final Logger log = LoggerFactory.getLogger(InterceptorConfig.class);
	
	
	/**
	 * 进入controller层之前拦截请求
	 * 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param o
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o)
			throws Exception {
		log.info("---------------------开始进入请求地址拦截----------------------------");
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/JavaScript; charset=utf-8");
		return true;
//        String method = request.getMethod();
//        
//        
//        String uri = request.getRequestURI();
//        if (SecurityUtils.getSubject().getPrincipal() == null) {
//        	return false;
//        }
//		response.getWriter().write(JSON.toJSONString(ResultConstant.ofFail(ResultConstant.FAIL_CODE_TOKEN_ERROR, 
//										ResultConstant.FAIL_CODE_TOKEN_ERROR_DESC)));
//    	return false;

	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {
	}
}
