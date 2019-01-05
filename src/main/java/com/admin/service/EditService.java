package com.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
		List<Object[]> edits = editRepository.findConstantId(constantId);
		for (Object[] objs : edits) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", objs[0]);
			map.put("title", objs[1]);
			map.put("subTitle", objs[2]);
			map.put("time", objs[3]);
			map.put("detail", objs[4]);
			map.put("imgUrl", objs[5]);
			list.add(map);
		}
		return list;
	}
	
	@Transactional
	public List<Map<String, Object>> findEditByNum(String constantId) {
		List<Map<String, Object>> list = new ArrayList<>();
		List<Object[]> edits = editRepository.findConstantIdLimit(constantId);
		for (Object[] objs : edits) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", objs[0]);
			map.put("title", objs[1]);
			map.put("subTitle", objs[2]);
			map.put("time", objs[3]);
			map.put("detail", objs[4]);
			map.put("imgUrl", objs[5]);
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
		map.put("constantId", edit.getConstantId());
		return map;
	}
	
	
	@Transactional
	public Page<Map<String, Object>> getEdit(Integer page, Integer size) {
		Page<Object[]> edits= editRepository.findConstantIdPage(PageRequest.of(page, size));
		List<Map<String, Object>> list = new ArrayList<>();
		for (Object[] objs : edits.getContent()) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", objs[0]);
			map.put("constantId", objs[1]);
			map.put("title", objs[2]);
			map.put("subTitle", objs[3]);
			map.put("time", objs[4]);
			map.put("detail", objs[5]);
			map.put("imgUrl", objs[6]);
			map.put("constantName", constantRepository.findByConstantId(objs[1].toString()).get(0).getConstantName());
			list.add(map);
		}
		return new PageImpl<Map<String, Object>>(list, PageRequest.of(page, size), edits.getTotalElements());
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
