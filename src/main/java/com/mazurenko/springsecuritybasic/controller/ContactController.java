package com.mazurenko.springsecuritybasic.controller;

import com.mazurenko.springsecuritybasic.entity.ContactMessages;
import com.mazurenko.springsecuritybasic.repository.ContactMessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
public class ContactController {

    private ContactMessagesRepository contactRepository;

    @Autowired
    public ContactController(ContactMessagesRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @PostMapping("/contact")
    public ContactMessages saveContactDetails(@RequestBody ContactMessages contact) {
        contact.setContactId(getServiceReqNumber());
        contact.setCreateDt(new Date(System.currentTimeMillis()));
        return contactRepository.save(contact);
    }

    private String getServiceReqNumber() {
        Random random = new Random();
        int ranNum = random.nextInt(9999999 - 9999) + 9999;
        return "SR" + ranNum;
    }
}
