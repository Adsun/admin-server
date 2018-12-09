package com.admin.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.admin.entity.Course;
import com.admin.entity.Teacher;
import com.admin.repository.TeacherRepository;

@Service
public class TeacherService {
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Transactional
	public List<Teacher> getAllTeachers() {
		return teacherRepository.findAll();
	}
	
	@Transactional
	public Page<Teacher> getTeacher(Integer page, Integer size) {
		Page<Teacher> teachers= teacherRepository.findAll(PageRequest.of(page, size));
		return teachers;
	}
	
	@Transactional
	public void updateTeacher(Teacher teacher) {
		Teacher tbTeacher = teacherRepository.getOne(teacher.getId());
		tbTeacher.setDetail(teacher.getDetail());
		tbTeacher.setImgUrl(teacher.getImgUrl());
		tbTeacher.setLink(teacher.getLink());
		tbTeacher.setName(teacher.getName());
		tbTeacher.setSummary(teacher.getSummary());
		tbTeacher.setTitle(teacher.getTitle());
		teacherRepository.updateEntity(tbTeacher);
	}
	
	@Transactional
	public void deleteTeacher(Long id) {
		teacherRepository.deleteEntityById(id);
	}
	
	@Transactional
	public void addTeacher(Teacher teacher) {
		teacherRepository.createEntity(teacher);
	}

}
