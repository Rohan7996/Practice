package com.johnson.app.rest.Service;

import com.johnson.app.rest.Repository.ContactRepository;
import com.johnson.app.rest.model.Contact;
import com.johnson.app.rest.model.ContactRequest;
import com.johnson.app.rest.response.ConsolidatedContactResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;
    public ConsolidatedContactResponse identifyAndUpdateContact(ContactRequest request) {
        Optional<Contact> existingContact = contactRepository.findByEmailOrPhoneNumber(request.getEmail(), request.getPhoneNumber());

        if (existingContact.isPresent()) {
            Contact primaryContact = existingContact.get();
            Contact secondaryContact = new Contact();
            secondaryContact.setPhoneNumber(primaryContact.getPhoneNumber());
            secondaryContact.setEmail(primaryContact.getEmail());
            secondaryContact.setLinkedId(primaryContact.getId());
            secondaryContact.setLinkPrecedence("secondary");
            secondaryContact.setCreatedAt(primaryContact.getCreatedAt());
            secondaryContact.setUpdatedAt(LocalDateTime.now());

            contactRepository.save(secondaryContact);

            primaryContact.setUpdatedAt(LocalDateTime.now());
            contactRepository.save(primaryContact);

            return new ConsolidatedContactResponse(primaryContact, Arrays.asList(secondaryContact.getId()));
        }
        else {
            Contact newContact = new Contact();
            newContact.setPhoneNumber(request.getPhoneNumber());
            newContact.setEmail(request.getEmail());
            newContact.setLinkPrecedence("primary");
            newContact.setCreatedAt(LocalDateTime.now());
            newContact.setUpdatedAt(LocalDateTime.now());

            Contact savedContact = contactRepository.save(newContact);

            return new ConsolidatedContactResponse(savedContact, new ArrayList<>());
        }
    }

}
