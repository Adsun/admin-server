package com.admin.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.admin.entity.Resource;
import com.admin.repository.ConstantRepository;
import com.admin.repository.ResourceRepository;

@Service
public class ResourceService {
	@Autowired
	private ResourceRepository resourceRepository;
	@Autowired
	private ConstantRepository constantRepository;
	public List<Resource> findResource(String constantId, Boolean free) {
		if (free != null && free) {
			return resourceRepository.findByBdyUrlNotNull();
		} else {
			return resourceRepository.findByConstantId(constantId);
		}
	}
	
	@Transactional
	public Page<Resource> getResource(Integer page, Integer size) {
		Page<Resource> resources = resourceRepository.findAll(PageRequest.of(page, size));
		for (Resource resource : resources.getContent()) {
			resource.setConstantName(constantRepository.findByConstantId(resource.getConstantId()).get(0).getConstantName());
		}
		return resources;
	}
	
	@Transactional
	public void updateResource(Resource resource) {
		Resource tbResource = resourceRepository.getOne(resource.getId());
		tbResource.setBdyCode(resource.getBdyCode());
		tbResource.setBdyUrl(resource.getBdyUrl());
		tbResource.setConstantId(resource.getConstantId());
		tbResource.setImgUrl(resource.getImgUrl());
		tbResource.setPlayUrl(resource.getPlayUrl());
		tbResource.setQrcUrl(resource.getQrcUrl());
		tbResource.setResContent(resource.getResContent());
		tbResource.setResName(resource.getResName());
		tbResource.setVideoUrl(resource.getVideoUrl());
		resourceRepository.updateEntity(tbResource);
	}
	
	@Transactional
	public void deleteResource(Long id) {
		resourceRepository.deleteEntityById(id);
	}
	
	@Transactional
	public void addResource(Resource resource) {
		resourceRepository.createEntity(resource);
	}
}
