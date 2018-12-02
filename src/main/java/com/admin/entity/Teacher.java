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
	private Integer teacherId;
	private String name;
	private String summary;
	private String detail;
	private String imgUrl;
	private String link;
	private String title;
}
