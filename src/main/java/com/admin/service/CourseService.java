package com.admin.service;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.admin.entity.Course;
import com.admin.entity.Course;
import com.admin.repository.ConstantRepository;
import com.admin.repository.CourseRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private ConstantRepository constantRepository;
	
	@Transactional
	public List<Course> findCourse(String constantId) {
		return courseRepository.findByConstantId(constantId);
	}
	
	@Transactional
	public Page<Course> getStantCourse(Integer page, Integer size) {
		List<String> constantIds = Arrays.asList("courseType1","courseType2","courseType3","courseType4","courseType5","courseType6");
		return courseRepository.findByConstantIdInOrderByUpdateDatetimeDesc(constantIds,PageRequest.of(page, size));
	}
	
	@Transactional
	public Page<Course> getCourse(Integer page, Integer size) {
		Page<Course> courses= courseRepository.findAll(PageRequest.of(page, size));
		for (Course course : courses.getContent()) {
			course.setConstantName(constantRepository.findByConstantId(course.getConstantId()).get(0).getConstantName());
		}
		return courses;
	}
	
	@Transactional
	public void updateCourse(Course course) {
		Course tbCourse = courseRepository.getOne(course.getId());
		tbCourse.setConstantId(course.getConstantId());
		tbCourse.setCourseDtl(course.getCourseDtl());
		tbCourse.setCourseName(course.getCourseName());
		tbCourse.setImgUrl(course.getImgUrl());
		tbCourse.setPrice(course.getPrice());
		tbCourse.setStartTime(course.getStartTime());
		tbCourse.setTeacher(course.getTeacher());
		tbCourse.setTeacherDtl(course.getTeacherDtl());
		tbCourse.setTeacherImg(course.getTeacherImg());
		courseRepository.updateEntity(tbCourse);
	}
	
	@Transactional
	public void deleteCourse(Long id) {
		courseRepository.deleteEntityById(id);
	}
	
	@Transactional
	public void addCourse(Course course) {
		courseRepository.createEntity(course);
	}
}
