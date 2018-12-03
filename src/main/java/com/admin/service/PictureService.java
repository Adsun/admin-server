package com.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.admin.entity.Picture;
import com.admin.repository.PictureRepository;

@Service
public class PictureService {
	@Autowired
	private PictureRepository pictureRepository;
	
	@Transactional
	public Map<String, Object> getAllPictures() {
		Map<String, Object> map = new HashMap<>();
		List<Picture> pictures = pictureRepository.findAll();
		for (Picture picture : pictures) {
			map.put(picture.getPicId(), picture);
		}
		return map;
	}
	
	@Transactional
	public Page<Picture> getPicture(Integer page, Integer size) {
		return pictureRepository.findAll(PageRequest.of(page, size));
	}
	
	@Transactional
	public void updatePicture(Picture picture) {
		Picture tbPicture = pictureRepository.getOne(picture.getId());
		tbPicture.setPath(picture.getPath());
		tbPicture.setUrl(picture.getUrl());
		pictureRepository.updateEntity(tbPicture);
	}
	
	@Transactional
	public void deletePicture(Long id) {
		pictureRepository.deleteById(id);
	}
	
	@Transactional
	public void addPicture(Picture picture) {
		pictureRepository.createEntity(picture);
	}
}
