package com.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.constant.ResultConstant;
import com.admin.entity.Course;
import com.admin.service.CoursePlanService;
import com.admin.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private CoursePlanService coursePlanService;
	
	@GetMapping
	public ResultConstant getCourse(@RequestParam Integer number, @RequestParam Integer size) {
		return ResultConstant.ofSuccess(courseService.getCourse(number-1, size));
	}
	
	@PostMapping
	public ResultConstant editCourse(@RequestBody Course course) {
		if (course.getId() != null) {
			courseService.updateCourse(course);
		} else {
			courseService.addCourse(course);
		}
		return ResultConstant.ofSuccess();
	}
	@DeleteMapping
	public ResultConstant deleteCourse(Long id) {
		courseService.deleteCourse(id);
		return ResultConstant.ofSuccess();
	}
}
