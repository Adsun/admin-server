package com.admin.entity;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "ssm_picture")
@SQLDelete(sql = "update ssm_picture set delete_ind = true where id=?")
@SQLDeleteAll(sql = "update ssm_picture set delete_ind = true where id=?")
@Where(clause = "delete_ind = false")
@Getter
@Setter
public class Picture extends AbstractEntity {

	private String path;// 文件路径
	private String url;// 跳转url
	private String picId;
	@Transient
	private String picName;
}
