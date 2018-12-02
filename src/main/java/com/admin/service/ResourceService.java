package com.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.entity.Resource;
import com.admin.repository.ResourceRepository;

@Service
public class ResourceService {
	@Autowired
	private ResourceRepository resourceRepository;
	public List<Resource> findResource(String constantId, Boolean free) {
		if (free != null && free) {
			return resourceRepository.findByBdyUrlNotNull();
		} else {
			return resourceRepository.findByConstantId(constantId);
		}
	}
}
