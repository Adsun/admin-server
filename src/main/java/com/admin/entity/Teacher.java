package com.admin.entity;

import javax.persistence.Entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "ssm_teacher")
@SQLDelete(sql = "update ssm_teacher set delete_ind = true where id=?")
@SQLDeleteAll(sql = "update ssm_teacher set delete_ind = true where id=?")
@Where(clause = "delete_ind = false")
@Getter
@Setter
public class Teacher extends AbstractEntity {
	private String name;//姓名
	private String summary;//简介
	private String detail;//介绍
	private String imgUrl;//图片
	private String link;//跳转链接
	private String title;//介绍标题
}
