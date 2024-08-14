package com.crud.phonebook.controller;

import com.crud.phonebook.model.Contact;
import com.crud.phonebook.response.ApiResponse;
import com.crud.phonebook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<ApiResponse<Contact>> createContact(@RequestBody Contact contact) {
        Contact createdContact = contactService.createContact(contact);
        ApiResponse<Contact> response = new ApiResponse<>(HttpStatus.CREATED.value(), "Contact created successfully", createdContact);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Contact>> getContactById(@PathVariable Long id) {
        Contact contact = contactService.getContactById(id);
        if (contact != null) {
            ApiResponse<Contact> response = new ApiResponse<>(HttpStatus.OK.value(), "Contact retrieved successfully", contact);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Contact> response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Contact not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Contact>>> getAllContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        ApiResponse<List<Contact>> response = new ApiResponse<>(HttpStatus.OK.value(), "Contacts retrieved successfully", contacts);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Contact>> updateContact(@PathVariable Long id, @RequestBody Contact contactDetails) {
        Contact updatedContact = contactService.updateContact(id, contactDetails);
        if (updatedContact != null) {
            ApiResponse<Contact> response = new ApiResponse<>(HttpStatus.OK.value(), "Contact updated successfully", updatedContact);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Contact> response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Contact not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteContact(@PathVariable Long id) {
        boolean isDeleted = contactService.deleteContact(id);
        if (isDeleted) {
            ApiResponse<Void> response = new ApiResponse<>(HttpStatus.OK.value(), "Contact deleted successfully", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<Void> response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Contact not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
