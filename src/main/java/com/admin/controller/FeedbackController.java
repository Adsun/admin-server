package com.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.constant.ResultConstant;
import com.admin.service.ContactService;
import com.admin.service.ErrortService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
	@Autowired
	private ErrortService errortService;
	
	@Autowired
	private ContactService contactService;
	
	@GetMapping
	public ResultConstant getFeedback(@RequestParam Integer type, @RequestParam Integer number, @RequestParam Integer size) {
		if (type == 1) {
			return ResultConstant.ofSuccess(errortService.getErrort(number-1, size));
		} else {
			return ResultConstant.ofSuccess(contactService.getContact(number-1, size));
		}
	}
	@DeleteMapping
	public ResultConstant deleteJob(Long id, Integer type) {
		if (type == 1) {
			errortService.deleteErrort(id);
		} else {
			contactService.deleteContact(id);
		}
		return ResultConstant.ofSuccess();
	}
}
