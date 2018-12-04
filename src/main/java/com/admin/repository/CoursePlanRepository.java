package com.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.admin.entity.CoursePlan;

@Repository
public interface CoursePlanRepository extends BaseRepository<CoursePlan, Long> {
	public List<CoursePlan> findByCourseIdOrderByUpdateDatetimeDesc(Integer courseId);
	public Page<CoursePlan> findByCourseId(Integer courseId, Pageable pageable);
}
