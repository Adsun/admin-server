package com.admin.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.admin.constant.ResultConstant;
import com.admin.entity.UserInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author fengxiang
 * @date 2018-10-23
 */
@Api(value = "api", tags = "登录模块相关API")
@RestController
public class LoginController {
	
	@ApiOperation(value = "登录", notes = "根据用户名userName，用户输入的验证短信loginMsg，来进行登录请求")
	@PostMapping("/login")
	public ResultConstant login(
			@RequestBody UserInfo userInfo
			) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUserName(), userInfo.getPassWord().toCharArray());
		if (userInfo.getRememberMe() != null) {
			token.setRememberMe(userInfo.getRememberMe());
		}
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, e.getMessage());
		}
		return ResultConstant.ofSuccess();
	}
	
	@ApiOperation(value = "登出", notes = "登出")
	@GetMapping("/logout") 
	public ResultConstant logout(){
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return ResultConstant.ofSuccess();
	}
	
	/*
	 * 用于刷新登录信息
	 */
	@ApiOperation(value = "验证登录状态", notes = "用于验证和刷新登录状态，用户每次打开主页时请求一次，如果用户已登录，会返回用户的模块权限，用户页面模块展示")
	@GetMapping("/checkAuth")
	public ResultConstant checkAuth() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isRemembered()) {
			DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
			CookieRememberMeManager rememberMeManager = (CookieRememberMeManager) securityManager.getRememberMeManager();
			SimpleAuthenticationInfo authcInfo = new SimpleAuthenticationInfo();
			authcInfo.setPrincipals(subject.getPrincipals());
			rememberMeManager.rememberIdentity(subject, null, authcInfo);
		}
		UserInfo userInfo = (UserInfo) subject.getPrincipal();
		return ResultConstant.ofSuccess(userInfo.getFullName());
	}
	
	@GetMapping("/unauth")
    public ResultConstant unauth() {
		return ResultConstant.ofFail(ResultConstant.FAIL_CODE_LOGIN_ERROR, ResultConstant.FAIL_CODE_LOGIN_ERROR_DESC);
    }
}
