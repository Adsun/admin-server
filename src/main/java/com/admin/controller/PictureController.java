package com.admin.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.admin.constant.ResultConstant;
import com.admin.entity.Picture;
import com.admin.service.PictureService;
import com.alibaba.fastjson.JSON;

@RestController
@RequestMapping("/picture")
public class PictureController {
	@Autowired
	private PictureService pictureService;
	
	@GetMapping
	public ResultConstant getPicture(@RequestParam Integer number, @RequestParam Integer size) {
		return ResultConstant.ofSuccess(pictureService.getPicture(number-1, size));
	}
	
	@PostMapping("/upload")
	public ResultConstant uploadPicture(MultipartFile file) {
		return ResultConstant.ofSuccess(pictureService.uploadPicture(file));
	}
	
	@PostMapping("/uploadFile")
	public String uploadFile(MultipartFile file) {
		Map<String, Object> res = new HashMap<>();
		res.put("errno", 0);
		res.put("data", Arrays.asList(pictureService.uploadPicture(file)));
		return JSON.toJSONString(res);
	}
	
	@PostMapping
	public ResultConstant editPicture(@RequestBody Picture picture) {
		if (StringUtils.isEmpty(picture.getPicId())) {
			return ResultConstant.ofFail(ResultConstant.FAIL_CODE_PARAM_ERROR, "类别或名称为空");
		}
		if (picture.getId() != null) {
			pictureService.updatePicture(picture);
		} else {
			pictureService.addPicture(picture);
		}
		return ResultConstant.ofSuccess();
	}
	@DeleteMapping
	public ResultConstant deletePicture(Long id) {
		pictureService.deletePicture(id);
		return ResultConstant.ofSuccess();
	}
}
