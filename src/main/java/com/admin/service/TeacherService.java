package com.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.entity.Teacher;
import com.admin.repository.TeacherRepository;

@Service
public class TeacherService {
	@Autowired
	private TeacherRepository teacherRepository;
	
	public List<Teacher> getAllTeachers() {
		return teacherRepository.findAll();
	}

}
