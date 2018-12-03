package com.admin.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "ssm_course")
@SQLDelete(sql = "update ssm_course set delete_ind = true where id=?")
@SQLDeleteAll(sql = "update ssm_course set delete_ind = true where id=?")
@Where(clause = "delete_ind = false")
@Getter
@Setter
public class Course extends AbstractEntity {
	private String courseName;
	@Transient
	private String constantName;
	private String constantId;
	private String startTime;
	private String teacher;
	private String price;
	private String courseDtl;
	private String imgUrl;
	private String teacherDtl;
	private String teacherImg;
}
