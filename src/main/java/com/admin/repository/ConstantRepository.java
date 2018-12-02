package com.admin.repository;

import javax.persistence.Entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;
import org.springframework.stereotype.Repository;

import com.admin.entity.Article;
import com.admin.entity.Constant;

import lombok.Getter;
import lombok.Setter;

@Repository
public interface ConstantRepository extends BaseRepository<Constant, Long> {
	
}
