package com.admin.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.constant.ResultConstant;
import com.admin.repository.ConstantRepository;

@RestController
@RequestMapping("/constant")
public class ConstantController {
	@Autowired
	private ConstantRepository constantRepository;
	
	@GetMapping
	public ResultConstant getConstants(String types) {
		
		List<Integer> typeList = new ArrayList<>();
		for (String type : types.split(",")) {
			typeList.add(Integer.valueOf(type));
		}
		return ResultConstant.ofSuccess(constantRepository.findByConstantTypeIn(typeList));
	}
}
