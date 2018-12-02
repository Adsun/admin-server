package com.admin.entity;

import javax.persistence.Entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "ssm_contact")
@SQLDelete(sql = "update ssm_contact set delete_ind = true where id=?")
@SQLDeleteAll(sql = "update ssm_contact set delete_ind = true where id=?")
@Where(clause = "delete_ind = false")
@Getter
@Setter
public class Contact extends AbstractEntity {
	private String name;
	private String city;
	private String email;
	private String phone;
	private String detail;
}
