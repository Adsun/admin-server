package com.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.admin.entity.Edit;
import com.admin.repository.ConstantRepository;
import com.admin.repository.EditRepository;

@Service
public class EditService {
	@Autowired
	private EditRepository editRepository;
	@Autowired
	private ConstantRepository constantRepository;
	
	@Transactional
	public List<Map<String, Object>> findEdit(String constantId) {
		List<Map<String, Object>> list = new ArrayList<>();
		List<Edit> edits = editRepository.findByConstantId(constantId);
		for (Edit edit : edits) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", edit.getId());
			map.put("title", edit.getTitle());
			map.put("subTitle", edit.getSubTitle());
			map.put("time", edit.getTime());
			map.put("detail", edit.getDetail());
			map.put("imgUrl", edit.getImgUrl());
			list.add(map);
		}
		return list;
	}
	
	@Transactional
//	@Cacheable(value = "edits", key = "#id")
	public Map<String, Object> findEditById(Long id) {
		Edit edit = editRepository.getOne(id);
		Map<String, Object> map = new HashMap<>();
		
		map.put("contextStr", edit.getContext() == null ? "" : new String(edit.getContext()));
		map.put("title", edit.getTitle());
		map.put("subTitle", edit.getSubTitle());
		map.put("time", edit.getTime());
		map.put("detail", edit.getDetail());
		map.put("imgUrl", edit.getImgUrl());
		return map;
	}
	
	@Transactional
	public Page<Edit> getEdit(Integer page, Integer size) {
		Page<Edit> edits= editRepository.findAll(PageRequest.of(page, size));
		for (Edit edit : edits.getContent()) {
			if (edit.getContext() != null) {
				edit.setContextStr(new String(edit.getContext()));
			}
			edit.setConstantName(constantRepository.findByConstantId(edit.getConstantId()).get(0).getConstantName());
		}
		return edits;
	}
	
	@Transactional
//	@CacheEvict(value = "edits", key = "#edit.id")
	public void updateEdit(Edit edit) {
		Edit tbEdit = editRepository.getOne(edit.getId());
		tbEdit.setConstantId(edit.getConstantId());
		tbEdit.setContextStr(edit.getContextStr());
		tbEdit.setTitle(edit.getTitle());
		tbEdit.setTime(edit.getTime());
		tbEdit.setSubTitle(edit.getSubTitle());
		tbEdit.setImgUrl(edit.getImgUrl());
		tbEdit.setDetail(edit.getDetail());
		if (edit.getContextStr() != null) {
			tbEdit.setContext(edit.getContextStr().getBytes());
		}
		editRepository.updateEntity(tbEdit);
	}
	
	@Transactional
//	@CacheEvict(value = "edits", key = "#id")
	public void deleteEdit(Long id) {
		editRepository.deleteEntityById(id);
	}
	
	@Transactional
	public void addEdit(Edit edit) {
		edit.setContext(edit.getContextStr().getBytes());
		editRepository.createEntity(edit);
	}
}
