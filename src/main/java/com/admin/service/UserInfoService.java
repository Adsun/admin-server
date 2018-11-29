package com.admin.service;

import javax.transaction.Transactional;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.admin.constant.ResultConstant;
import com.admin.entity.UserInfo;
import com.admin.repository.UserInfoRepository;


/**
 * @author fengxiang
 * @date 2018-10-22
 */
@Service
public class UserInfoService {
	@Autowired
	private UserInfoRepository userInfoRepository;

	@Transactional
	public ResultConstant getUserInfo(Integer page, Integer size) {
		Page<UserInfo> userInfos = userInfoRepository.findAll(PageRequest.of(page, size));
		
		return ResultConstant
				.ofSuccess(userInfos);
	}
	
	@Transactional
	public void deleteUserInfo(Long userId) {
		userInfoRepository.deleteEntityById(userId);
	}
	
	@Transactional
	public void addUserInfo(UserInfo userInfo) {
		userInfoRepository.createEntity(userInfo);
	}

	@Transactional
	public void updateUserInfo(UserInfo userInfo) {
		UserInfo tbUserInfo = userInfoRepository.getOne(userInfo.getId());
		tbUserInfo.setAdminInd(userInfo.getAdminInd());
		tbUserInfo.setFullName(tbUserInfo.getFullName());
		tbUserInfo.setPassWord(tbUserInfo.getPassWord());
		userInfoRepository.updateEntity(userInfo);
	}
	
	public UserInfo getCurrentUser() {
		Subject subject = SecurityUtils.getSubject();
		UserInfo userInfo = (UserInfo) subject.getPrincipal();
		return userInfo;
	}
}
