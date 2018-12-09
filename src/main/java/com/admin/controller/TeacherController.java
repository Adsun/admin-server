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
import com.admin.entity.Teacher;
import com.admin.service.TeacherService;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	@GetMapping
	public ResultConstant getTeacher(@RequestParam Integer number, @RequestParam Integer size) {
		return ResultConstant.ofSuccess(teacherService.getTeacher(number-1, size));
	}
	
	@PostMapping
	public ResultConstant updateTeacher(@RequestBody Teacher teacher) {
		if (teacher.getId() != null) {
			teacherService.updateTeacher(teacher);
		} else {
			teacherService.addTeacher(teacher);
		}
		return ResultConstant.ofSuccess();
	}
	
	@DeleteMapping
	public ResultConstant deleteTeacher(Long id) {
		teacherService.deleteTeacher(id);
		return ResultConstant.ofSuccess();
	}

}
