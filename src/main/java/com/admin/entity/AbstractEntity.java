package com.admin.entity;

import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity 基础类
 * @author fengxiang
 * @date 2018-10-22
 */
@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity {

	@Id
    @GeneratedValue
    protected Long id;
	
	protected String createdBy;
	
	protected Date createDatetime;
	
	protected String updateBy;
	
	protected Date updateDatetime;
	
	protected Boolean deleteInd = false;
	

	public void createEntity() {
		Subject subject = SecurityUtils.getSubject();
		if (subject == null || subject.getPrincipal() == null) {
			setCreatedBy("sys");
			setCreateDatetime(new Date());
			setUpdateBy("sys");
			setUpdateDatetime(new Date());
		} else {
			UserInfo userInfo = (UserInfo) subject.getPrincipal();
			setCreatedBy(userInfo.getFullName());
			setCreateDatetime(new Date());
			setUpdateBy(userInfo.getFullName());
			setUpdateDatetime(new Date());
		}
	}
	
	public void updateEntity() {
		Subject subject = SecurityUtils.getSubject();
		if (subject == null || subject.getPrincipal() == null) {
			setUpdateBy("sys");
			setUpdateDatetime(new Date());
		} else {
			UserInfo userInfo = (UserInfo) subject.getPrincipal();
			setUpdateBy(userInfo.getFullName());
			setUpdateDatetime(new Date());
		}
	}
	
	public void deleteEntity() {
		Subject subject = SecurityUtils.getSubject();
		if (subject == null || subject.getPrincipal() == null) {
			setUpdateBy("sys");
			setUpdateDatetime(new Date());
		} else {
			UserInfo userInfo = (UserInfo) subject.getPrincipal();
			setUpdateBy(userInfo.getFullName());
			setUpdateDatetime(new Date());
		}
		
	}
}
