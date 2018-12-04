package com.admin.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	
	@Transactional
	public Page<Job> getJob(Integer page, Integer size) {
		Page<Job> Jobs = jobRepository.findAll(PageRequest.of(page, size));
		return Jobs;
	}
	
	@Transactional
	public void updateJob(Job job) {
		Job tbJob = jobRepository.getOne(job.getId());
		tbJob.setName(job.getName());
		tbJob.setEName(job.getEName());
		tbJob.setJobDtl(job.getJobDtl());
		jobRepository.updateEntity(tbJob);
	}
	
	@Transactional
	public void deleteJob(Long id) {
		jobRepository.deleteEntityById(id);
	}
	
	@Transactional
	public void addJob(Job job) {
		jobRepository.createEntity(job);
	}

}
