package com.johnson.app.rest.response;

import com.johnson.app.rest.model.Contact;
import java.util.Arrays;
import java.util.List;

public class ConsolidatedContactResponse {
    private Long primaryContactId;
    private List<String> emails;
    private List<String> phoneNumbers;
    private List<Long> secondaryContactIds;

    public ConsolidatedContactResponse(Contact primaryContact, List<Long> secondaryContactIds) {
        this.primaryContactId = primaryContact.getId();
        this.emails = Arrays.asList(primaryContact.getEmail());
        this.phoneNumbers = Arrays.asList(primaryContact.getPhoneNumber());
        this.secondaryContactIds = secondaryContactIds;
    }

    public Long getPrimaryContactId() {
        return primaryContactId;
    }

    public void setPrimaryContactId(Long primaryContactId) {
        this.primaryContactId = primaryContactId;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<Long> getSecondaryContactIds() {
        return secondaryContactIds;
    }

    public void setSecondaryContactIds(List<Long> secondaryContactIds) {
        this.secondaryContactIds = secondaryContactIds;
    }
}
