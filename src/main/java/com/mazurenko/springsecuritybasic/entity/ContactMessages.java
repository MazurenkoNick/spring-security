package com.mazurenko.springsecuritybasic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="contact_messages")
public class ContactMessages {

    @Id
    @Column(name="contact_id")
    private String contactId;

    @Column(name="contact_name")
    private String contactName;

    @Column(name="contact_email")
    private String contactEmail;

    @Column(name="subject")
    private String subject;

    @Column(name="message")
    private String message;

    @Column(name="create_dt")
    private Date createDt;
}
