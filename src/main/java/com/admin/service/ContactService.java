package com.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.entity.Contact;
import com.admin.repository.ContactRepository;

@Service
public class ContactService {
	@Autowired
	private ContactRepository contactRepository;
	public void addContact(Contact contact) {
		contactRepository.createEntity(contact);
	}
}
