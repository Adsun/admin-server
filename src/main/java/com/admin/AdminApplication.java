package com.admin;

import java.util.List;
import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.admin.entity.UserInfo;
import com.admin.repository.UserInfoRepository;
import com.admin.util.EncryptUtils;

import org.apache.shiro.mgt.SecurityManager;


/**
 * @author fengxiang
 * @date 2018-09-13
 */
@SpringBootApplication
public class AdminApplication implements CommandLineRunner {
	
	@Autowired
	private SecurityManager securityManager;
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Value("${admin.userName}")
	private String adminName;
	
	@Value("${admin.passWord}")
	private String adminWord;
	
	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}

	/**
	 * 项目初始化
	 */
	@Override
	@Transactional
	public void run(String... args) throws Exception {
		//初始化SecurityUtils
		SecurityUtils.setSecurityManager(securityManager);
		
		//初始化admin用户
		List<UserInfo> userInfos = userInfoRepository.findByUserName(adminName);
		if (CollectionUtils.isEmpty(userInfos)) {
			UserInfo userInfo = new UserInfo();
			userInfo.setFullName("系统管理员");
			userInfo.setUserName(adminName);
			userInfo.setPassWord(EncryptUtils.md5Encrypt(adminWord));
			userInfo.setAdminInd(true);
			userInfoRepository.createEntity(userInfo);
		}
		
	}
}
