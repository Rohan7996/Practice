package com.johnson.app.rest.Repository;

import com.johnson.app.rest.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByEmailOrPhoneNumber(String email, String phoneNumber);
}
