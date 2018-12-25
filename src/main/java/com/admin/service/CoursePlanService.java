package com.admin.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.admin.entity.CoursePlan;
import com.admin.repository.CoursePlanRepository;

@Service
public class CoursePlanService {
	@Autowired
	private CoursePlanRepository coursePlanRepository;
	
	@Transactional
	public List<CoursePlan> findCoursePlan(Integer courseId){
		return coursePlanRepository.findByCourseIdOrderByUpdateDatetimeDesc(courseId);
	}
	
	@Transactional
	public Page<CoursePlan> getCourse(Integer courseId, Integer page, Integer size) {
		Page<CoursePlan> coursePlans= coursePlanRepository.findByCourseIdOrderByUpdateDatetimeDesc(courseId, PageRequest.of(page, size));
		
		return coursePlans;
	}
	
	@Transactional
	public void updateCoursePlan(CoursePlan coursePlan) {
		CoursePlan tbCoursePlan = coursePlanRepository.getOne(coursePlan.getId());
		tbCoursePlan.setCourseDate(coursePlan.getCourseDate());
		tbCoursePlan.setCourseDtl(coursePlan.getCourseDtl());
		tbCoursePlan.setCourseTime(coursePlan.getCourseTime());
		coursePlanRepository.updateEntity(tbCoursePlan);
	}
	
	@Transactional
	public void deleteCoursePlan(Long id) {
		coursePlanRepository.deleteEntityById(id);
	}
	
	@Transactional
	public void addCoursePlan(CoursePlan coursePlan) {
		coursePlanRepository.createEntity(coursePlan);
	}
	
}
