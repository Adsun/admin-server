package com.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.entity.Errort;
import com.admin.repository.ErrortRepository;

@Service
public class ErrortService {

	@Autowired
	private ErrortRepository errortRepository;
	
	public void addErrort(Errort errort) {
		errortRepository.createEntity(errort);
	}
}
