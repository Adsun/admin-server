package com.admin.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.admin.entity.Contact;
import com.admin.entity.Errort;
import com.admin.repository.ContactRepository;

@Service
public class ContactService {
	@Autowired
	private ContactRepository contactRepository;
	
	@Transactional
	public Page<Contact> getContact(Integer page, Integer size) {
		Page<Contact> contacts = contactRepository.findAll(PageRequest.of(page, size));
		return contacts;
	}
	
	@Transactional
	public void addContact(Contact contact) {
		contactRepository.createEntity(contact);
	}
	
	@Transactional
	public void deleteContact(Long id) {
		contactRepository.deleteEntityById(id);
	}
}
