package com.mazurenko.springsecuritybasic.repository;

import com.mazurenko.springsecuritybasic.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    public List<Customer> findAllByEmail (String email);

    Customer findByEmail(String email);
}
