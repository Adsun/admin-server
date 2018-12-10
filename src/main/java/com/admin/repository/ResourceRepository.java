package com.admin.repository;

import java.util.List;

import javax.persistence.Entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;
import org.springframework.stereotype.Repository;

import com.admin.entity.Constant;
import com.admin.entity.Resource;

import lombok.Getter;
import lombok.Setter;

@Repository
public interface ResourceRepository extends BaseRepository<Resource, Long> {
	public List<Resource> findByConstantId(String constantId);
	public List<Resource> findByBdyUrlNotNullAndBdyUrlNot(String bdyUrl);
}
