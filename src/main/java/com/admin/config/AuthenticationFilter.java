package com.admin.config;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义filter
 * @author fengxiang
 * @date 2018-11-19
 */
public class AuthenticationFilter extends UserFilter {
    Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
    
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		HttpServletRequest httpReq = WebUtils.toHttp(request);
		/**过滤跨域预检**/
        if (httpReq.getMethod().equals( "OPTIONS" )) {
            return true;
        }
		return super.isAccessAllowed(request, response, mappedValue);
	}



	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletResponse httpResp = WebUtils.toHttp(response);
		HttpServletRequest httpReq = WebUtils.toHttp(request);
        /**系统重定向会默认把请求头清空，这里通过拦截器重新设置请求头，解决跨域问题*/
		if (StringUtils.isEmpty(httpReq.getHeader("Access-Control-Allow-Origin"))) {
			httpResp.addHeader("Access-Control-Allow-Origin", httpReq.getHeader("Origin"));
	        httpResp.addHeader("Access-Control-Allow-Headers", "*");
	        httpResp.addHeader("Access-Control-Allow-Methods", "*");
	        httpResp.addHeader("Access-Control-Allow-Credentials", "true");
		}
        saveRequestAndRedirectToLogin(request, response);
        return false;
	}

   
}
