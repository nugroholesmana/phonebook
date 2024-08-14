package com.crud.phonebook.repository;

import com.crud.phonebook.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByPhoneNumber(String phoneNumber);
    Optional<Contact> findByEmail(String email);
}
