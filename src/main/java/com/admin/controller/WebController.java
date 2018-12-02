package com.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.constant.ResultConstant;
import com.admin.entity.Contact;
import com.admin.entity.Errort;
import com.admin.service.ArticleService;
import com.admin.service.ContactService;
import com.admin.service.CoursePlanService;
import com.admin.service.CourseService;
import com.admin.service.ErrortService;
import com.admin.service.JobService;
import com.admin.service.PictureService;
import com.admin.service.ResourceService;
import com.admin.service.TeacherService;
@RestController
@RequestMapping("/web")
public class WebController {
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private PictureService pictureService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CoursePlanService coursePlanService;
	
	@Autowired
	private ErrortService errortService;
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private JobService jobService;
	
	@RequestMapping("/job")
	public ResultConstant getJob() throws Exception {
		return ResultConstant.ofSuccess(jobService.getAllJobs());
	}
	
	@RequestMapping("/teacher")
	public ResultConstant getTeacher() throws Exception {
		return ResultConstant.ofSuccess(teacherService .getAllTeachers());
	}
	
	@RequestMapping("/page")
	public ResultConstant getPageSetting() throws Exception {
		Map<String, Object> articleMap = articleService.getAllArticles();
		Map<String, Object> pictureMap = pictureService.getAllPictures();
		articleMap.putAll(pictureMap);
		return ResultConstant.ofSuccess(articleMap);
	}
	
	@RequestMapping("/stantCourse")
	public ResultConstant getCourseSetting(Integer page, Integer row) throws Exception {
		return ResultConstant.ofSuccess(courseService.getStantCourse(page-1, row));
	}
	
	@RequestMapping("/course")
	public ResultConstant getCourseSetting(String constantId) throws Exception {
		return ResultConstant.ofSuccess(courseService.findCourse(constantId));
	}
	
	@RequestMapping("/coursePlan")
	public ResultConstant getCoursePlanSetting(Integer courseId) throws Exception {
		return ResultConstant.ofSuccess(coursePlanService.findCoursePlan(courseId));
	}
	
	@RequestMapping("/resource")
	public ResultConstant getResourceSetting(String constantId, Boolean free) throws Exception {
		return ResultConstant.ofSuccess(resourceService.findResource(constantId, free));
	}
	
	@RequestMapping("/error")
	public ResultConstant addError(Errort errort) throws Exception {
		
		errortService.addErrort(errort);
		return ResultConstant.ofSuccess();
	}
	
	@RequestMapping("/contact")
	public ResultConstant addContact(Contact contact) throws Exception {
		contactService.addContact(contact);
		return ResultConstant.ofSuccess();
	}
	
	
}
