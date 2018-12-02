package com.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.entity.Picture;
import com.admin.repository.PictureRepository;

@Service
public class PictureService {
	@Autowired
	private PictureRepository pictureRepository;
	
	public Map<String, Object> getAllPictures() {
		Map<String, Object> map = new HashMap<>();
		List<Picture> pictures = pictureRepository.findAll();
		for (Picture picture : pictures) {
			map.put(picture.getPicId(), picture);
		}
		return map;
	}
}
