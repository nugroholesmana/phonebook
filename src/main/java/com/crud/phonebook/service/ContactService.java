package com.crud.phonebook.service;

import com.crud.phonebook.model.Contact;

import java.util.List;

public interface ContactService {
    Contact createContact(Contact contact);
    Contact updateContact(Long id, Contact contactDetails);
    Contact getContactById(Long id);
    List<Contact> getAllContacts();
    boolean deleteContact(Long id);
}