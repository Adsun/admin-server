package com.admin.entity;

import javax.persistence.Entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "ssm_error")
@SQLDelete(sql = "update ssm_error set delete_ind = true where id=?")
@SQLDeleteAll(sql = "update ssm_error set delete_ind = true where id=?")
@Where(clause = "delete_ind = false")
@Getter
@Setter
public class Errort extends AbstractEntity {
	
	private String name;
	private String detail;
	
}
