package com.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
	
	@GetMapping("/byId")
	public ResultConstant getEditById(Long id) {
		return ResultConstant.ofSuccess(editService.findEditById(id));
	}
	
	@PostMapping
	public ResultConstant updateEdit(@RequestBody Edit edit) {
		if (StringUtils.isEmpty(edit.getConstantId())) {
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "类别或名称为空");
		}
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
