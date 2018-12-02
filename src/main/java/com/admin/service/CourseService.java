package com.admin.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.admin.entity.Course;
import com.admin.repository.CourseRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courseRepository;
	
	public List<Course> findCourse(String constantId) {
		return courseRepository.findByConstantId(constantId);
	}
	
	public Page<Course> getStantCourse(Integer page, Integer size) {
		List<String> constantIds = Arrays.asList("courseType1","courseType2","courseType3","courseType4","courseType5","courseType6");
		return courseRepository.findByConstantIdInOrderByUpdateDatetimeDesc(constantIds,PageRequest.of(page, size));
	}
}
