package com.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.admin.entity.Course;

@Repository
public interface CourseRepository extends BaseRepository<Course, Long> {
	public List<Course> findByConstantId(String constantId);
	public Page<Course> findByConstantIdInOrderByUpdateDatetimeDesc(List<String> constantIds, Pageable pageable);
}
