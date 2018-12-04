package com.admin.config;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import com.admin.entity.UserInfo;
import com.admin.repository.UserInfoRepository;
import com.admin.util.EncryptUtils;

/**
 * @author fengxiang
 * @date 2018-10-22
 */
@Configuration
public class MyShiroRealm extends AuthorizingRealm {
	private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		return null;
	}
	
	@Transactional
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
	        UsernamePasswordToken up = (UsernamePasswordToken) token;
	        String userName = up.getUsername();
	        List<UserInfo> userInfos = userInfoRepository.findByUserName(userName);
	        if (CollectionUtils.isEmpty(userInfos)) {
	        	throw new AuthenticationException("用户不存在");
	        }
	        UserInfo userInfo = userInfos.get(0);
	        String passWord = userInfo.getPassWord();
	        if (!passWord.equals(new String(up.getPassword()))) {
	        	throw new AuthenticationException("密码错误");
	        }
	        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userInfo, passWord.toCharArray(), getName());
	        return info;
	}

}
