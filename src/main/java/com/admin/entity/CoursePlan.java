package com.admin.entity;

import javax.persistence.Entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "ssm_course_plan")
@SQLDelete(sql = "update ssm_course_plan set delete_ind = true where id=?")
@SQLDeleteAll(sql = "update ssm_course_plan set delete_ind = true where id=?")
@Where(clause = "delete_ind = false")
@Getter
@Setter
public class CoursePlan extends AbstractEntity {
	private Integer courseId;
	private String courseDate;
	private String courseTime;
	private String courseDtl;
}
