package com.crud.phonebook.service.impl;

import com.crud.phonebook.model.Contact;
import com.crud.phonebook.repository.ContactRepository;
import com.crud.phonebook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    private static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9]{10,15}$"); // Validasi format nomor telepon

    @Override
    public Contact createContact(Contact contact) {
        validateContact(contact);
        checkUniqueContact(contact);
        return contactRepository.save(contact);
    }

    @Override
    public Contact updateContact(Long id, Contact contactDetails) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));

        contact.setFirstName(contactDetails.getFirstName());
        contact.setLastName(contactDetails.getLastName());
        contact.setPhoneNumber(contactDetails.getPhoneNumber());
        contact.setEmail(contactDetails.getEmail());
        contact.setAddress(contactDetails.getAddress());

        checkUniqueContact(contact, id);
        validateContact(contact);

        return contactRepository.save(contact);
    }

    @Override
    public Contact getContactById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found"));
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public boolean deleteContact(Long id) {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void validateContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null");
        }
        if (!StringUtils.hasText(contact.getFirstName())) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        if (!StringUtils.hasText(contact.getLastName())) {
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        if (!PHONE_PATTERN.matcher(contact.getPhoneNumber()).matches()) {
            throw new IllegalArgumentException("Phone number is invalid");
        }
        if (contact.getEmail() != null && !contact.getEmail().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Email is invalid");
        }
    }

    private void checkUniqueContact(Contact contact) {
        if (contactRepository.findByPhoneNumber(contact.getPhoneNumber()).isPresent()) {
            throw new IllegalArgumentException("Phone number is already in use");
        }
        if (contactRepository.findByEmail(contact.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already in use");
        }
    }

    private void checkUniqueContact(Contact contact, Long id) {
        Contact existingByPhoneNumber = contactRepository.findByPhoneNumber(contact.getPhoneNumber()).orElse(null);
        if (existingByPhoneNumber != null && !existingByPhoneNumber.getId().equals(id)) {
            throw new IllegalArgumentException("Phone number is already in use");
        }
        Contact existingByEmail = contactRepository.findByEmail(contact.getEmail()).orElse(null);
        if (existingByEmail != null && !existingByEmail.getId().equals(id)) {
            throw new IllegalArgumentException("Email is already in use");
        }
    }
}
