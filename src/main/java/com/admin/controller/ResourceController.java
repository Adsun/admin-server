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
import com.admin.entity.Resource;
import com.admin.service.ResourceService;

@RestController
@RequestMapping("/resource")
public class ResourceController {
	@Autowired
	private ResourceService resourceService;
	
	@GetMapping
	public ResultConstant getResource(@RequestParam Integer number, @RequestParam Integer size) {
		return ResultConstant.ofSuccess(resourceService.getResource(number-1, size));
	}
	@PostMapping
	public ResultConstant editResource(@RequestBody Resource resource) {
		if (StringUtils.isEmpty(resource.getConstantId())) {
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "类别或名称为空");
		}
		if (resource.getId() != null) {
			resourceService.updateResource(resource);;
		} else {
			resourceService.addResource(resource);;
		}
		return ResultConstant.ofSuccess();
	}
	@DeleteMapping
	public ResultConstant deleteResource(Long id) {
		resourceService.deleteResource(id);
		return ResultConstant.ofSuccess();
	}
}
