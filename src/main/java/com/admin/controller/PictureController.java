package com.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.admin.constant.ResultConstant;
import com.admin.entity.Picture;
import com.admin.service.PictureService;

@Service
public class PictureController {
	@Autowired
	private PictureService pictureService;
	
	@GetMapping
	public ResultConstant getPicture(@RequestParam Integer number, @RequestParam Integer size) {
		return ResultConstant.ofSuccess(pictureService.getPicture(number-1, size));
	}
	
	@PostMapping
	public ResultConstant editPicture(@RequestBody Picture Picture) {
		if (Picture.getId() != null) {
			pictureService.updatePicture(Picture);
		} else {
			pictureService.addPicture(Picture);
		}
		return ResultConstant.ofSuccess();
	}
	@DeleteMapping
	public ResultConstant deletePicture(Long id) {
		pictureService.deletePicture(id);
		return ResultConstant.ofSuccess();
	}
}
