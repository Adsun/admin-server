package com.admin.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.admin.entity.Picture;
import com.admin.repository.ConstantRepository;
import com.admin.repository.PictureRepository;

@Service
public class PictureService {
	@Autowired
	private PictureRepository pictureRepository;
	@Autowired
	private ConstantRepository constantRepository;
	private static final String baseFilePath = "/data/upload/";
	private static final String baseFileUrl = "/upload/";

	@Transactional
	public Map<String, Object> getAllPictures() {
		Map<String, Object> map = new HashMap<>();
		List<Picture> pictures = pictureRepository.findAll();
		for (Picture picture : pictures) {
			map.put(picture.getPicId(), picture);
		}
		return map;
	}

	public String uploadPicture(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String path = baseFilePath + System.currentTimeMillis() + fileName;
		String url = baseFileUrl + System.currentTimeMillis() + fileName;
		File newFile = new File(path);
		try {
			if(!newFile.exists()) {
				newFile.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(newFile);
			out.write(file.getBytes());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return url;
	}

	@Transactional
	public Page<Picture> getPicture(Integer page, Integer size) {
		Page<Picture> pictures = pictureRepository.findAll(PageRequest.of(page, size));
		for (Picture picture : pictures.getContent()) {
			picture.setPicName(constantRepository.findByConstantId(picture.getPicId()).get(0).getConstantName());
		}
		return pictures;
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
