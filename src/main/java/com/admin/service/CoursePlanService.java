package com.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.entity.CoursePlan;
import com.admin.repository.CoursePlanRepository;

@Service
public class CoursePlanService {
	@Autowired
	private CoursePlanRepository coursePlanRepository;
	public List<CoursePlan> findCoursePlan(Integer courseId){
		return coursePlanRepository.findByCourseId(courseId);
	}
}
