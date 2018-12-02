package com.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.entity.Job;
import com.admin.repository.JobRepository;

@Service
public class JobService {
	@Autowired
	private JobRepository jobRepository;
	
	public List<Job> getAllJobs(){
		return jobRepository.findAll();
	}

}
