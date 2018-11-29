package com.admin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户信息
 * @author fengxiang
 * @date 2018-10-22
 */
@Entity(name = "tb_user_info")
@SQLDelete(sql = "update tb_user_info set delete_ind = true where id=?")
@SQLDeleteAll(sql = "update tb_user_info set delete_ind = true where id=?")
@Where(clause = "delete_ind = false")
@Getter
@Setter
public class UserInfo extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1477258102718817796L;
	//  用户姓名
	@Column(nullable = false)
    private String fullName;
//  用户名，这里使用的是手机号
    @Column(nullable = false, unique=true)
    private String userName;
//  用户密码 MD5
    @Column(nullable = false, unique=true)
    private String passWord;
    
    private Boolean adminInd = false;
    
    @Transient
    private Boolean rememberMe = false;
	
}
