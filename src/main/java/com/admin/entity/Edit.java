package com.admin.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Transient;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "ssm_edit")
@SQLDelete(sql = "update ssm_edit set delete_ind = true where id=?")
@SQLDeleteAll(sql = "update ssm_edit set delete_ind = true where id=?")
@Where(clause = "delete_ind = false")
@Getter
@Setter
public class Edit extends AbstractEntity{
	private String constantId;
	@Transient
	private String constantName;
	private String title;
	private String subTitle;
	private String detail;
	private String time;
	@Lob 
	@Basic(fetch = FetchType.LAZY) 
	@Column(columnDefinition="longblob") 
	private byte[] context;
	
	@Transient
	private String contextStr;
	
}
