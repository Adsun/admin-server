package com.admin.entity;

import javax.persistence.Entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "ssm_resource")
@SQLDelete(sql = "update ssm_resource set delete_ind = true where id=?")
@SQLDeleteAll(sql = "update ssm_resource set delete_ind = true where id=?")
@Where(clause = "delete_ind = false")
@Getter
@Setter
public class Resource extends AbstractEntity {
	private String resId;
	private String resName;
	private String constantId;
	private String resContent;
	private String videoUrl;
	private String qrcUrl;
	private String bdyUrl;
	private String bdyCode;
	private String imgUrl;
	private String playUrl;
}
