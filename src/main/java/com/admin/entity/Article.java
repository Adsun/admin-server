package com.admin.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "ssm_article")
@SQLDelete(sql = "update ssm_article set delete_ind = true where id=?")
@SQLDeleteAll(sql = "update ssm_article set delete_ind = true where id=?")
@Where(clause = "delete_ind = false")
@Getter
@Setter
public class Article extends AbstractEntity {
    private String articleId;
    @Transient
    private String articleName;
    private String articleContent;
    private String contentUrl;
}
