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
import com.admin.entity.Edit;
import com.admin.service.EditService;

@RestController
@RequestMapping("/edit")
public class EditController {
	@Autowired
	private EditService editService;
	
	@GetMapping
	public ResultConstant getEdit(@RequestParam Integer number, @RequestParam Integer size) {
		return ResultConstant.ofSuccess(editService.getEdit(number-1, size));
	}
	
	@PostMapping
	public ResultConstant updateEdit(@RequestBody Edit edit) {
		if (edit.getId() != null) {
			editService.updateEdit(edit);
		} else {
			editService.addEdit(edit);
		}
		return ResultConstant.ofSuccess();
	}
	
	@DeleteMapping
	public ResultConstant deleteEdit(Long id) {
		editService.deleteEdit(id);
		return ResultConstant.ofSuccess();
	}
}
