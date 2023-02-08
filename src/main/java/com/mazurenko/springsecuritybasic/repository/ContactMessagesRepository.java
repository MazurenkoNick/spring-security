package com.mazurenko.springsecuritybasic.repository;

import com.mazurenko.springsecuritybasic.entity.ContactMessages;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactMessagesRepository extends CrudRepository<ContactMessages, String> {
}
