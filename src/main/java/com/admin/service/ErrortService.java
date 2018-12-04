package com.admin.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.admin.entity.Errort;
import com.admin.entity.Job;
import com.admin.repository.ErrortRepository;

@Service
public class ErrortService {

	@Autowired
	private ErrortRepository errortRepository;
	
	@Transactional
	public Page<Errort> getErrort(Integer page, Integer size) {
		Page<Errort> errorts = errortRepository.findAll(PageRequest.of(page, size));
		return errorts;
	}
	
	@Transactional
	public void addErrort(Errort errort) {
		errortRepository.createEntity(errort);
	}
	
	@Transactional
	public void deleteErrort(Long id) {
		errortRepository.deleteEntityById(id);
	}
}
