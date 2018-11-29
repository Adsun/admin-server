package com.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.constant.ResultConstant;
import com.admin.entity.UserInfo;
import com.admin.service.UserInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * @author fengxiang
 * @date 2018-10-22
 */
@Api(value="api",tags="用户模块接口")
@RestController
@RequestMapping("/userManager")
public class UserInfoController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@ApiOperation("获取用户")
	@GetMapping
	public ResultConstant getUserInfo(
			@RequestParam Integer number, @RequestParam Integer size) {
		return userInfoService.getUserInfo(number-1, size);
	}
	@ApiOperation("新增和更新用户信息")
	@PostMapping
	public ResultConstant updateUserInfo(@RequestBody UserInfo userInfo) {
		if (userInfo.getId() != null) {
			userInfoService.updateUserInfo(userInfo);
		} else {
			userInfoService.addUserInfo(userInfo);
		}
		
		return ResultConstant.ofSuccess();
	}
	
	@ApiOperation("删除用户信息")
	@DeleteMapping
	public ResultConstant deleteUserInfo(@RequestParam Long userId) {
		userInfoService.deleteUserInfo(userId);
		return ResultConstant.ofSuccess();
	}
}
