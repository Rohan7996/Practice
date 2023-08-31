package com.johnson.app.rest.controller;

import com.johnson.app.rest.Service.ContactService;
import com.johnson.app.rest.model.ContactRequest;
import com.johnson.app.rest.response.ConsolidatedContactResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping("/identify")
    public ResponseEntity<ConsolidatedContactResponse> identifyContact(@RequestBody ContactRequest request) {
        ConsolidatedContactResponse response = contactService.identifyAndUpdateContact(request);
        return ResponseEntity.ok(response);
    }
}
