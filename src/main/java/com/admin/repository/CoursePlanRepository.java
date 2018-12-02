package com.admin.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;
import org.springframework.stereotype.Repository;

import com.admin.entity.Constant;
import com.admin.entity.CoursePlan;

import lombok.Getter;
import lombok.Setter;

@Repository
public interface CoursePlanRepository extends BaseRepository<CoursePlan, Long> {
	public List<CoursePlan> findByCourseId(Integer courseId);
}
