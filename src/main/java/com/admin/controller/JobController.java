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
import com.admin.entity.Job;
import com.admin.service.JobService;

@RestController
@RequestMapping("/job")
public class JobController {
	@Autowired
	private JobService jobService;
	
	@GetMapping
	public ResultConstant getJob(@RequestParam Integer number, @RequestParam Integer size) {
		return ResultConstant.ofSuccess(jobService.getJob(number-1, size));
	}
	@PostMapping
	public ResultConstant editJob(@RequestBody Job job) {
		if (job.getId() != null) {
			jobService.updateJob(job);;
		} else {
			jobService.addJob(job);;
		}
		return ResultConstant.ofSuccess();
	}
	@DeleteMapping
	public ResultConstant deleteJob(Long id) {
		jobService.deleteJob(id);
		return ResultConstant.ofSuccess();
	}
}
