package com.mazurenko.springsecuritybasic.service;

import com.mazurenko.springsecuritybasic.entity.Customer;
import com.mazurenko.springsecuritybasic.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void register(Customer customer) {
        String uniqueEmail = customer.getEmail();

        if (!isEmailUnique(uniqueEmail)) {
            throw new IllegalArgumentException("User with this email already exists");
        }
        customerRepository.save(customer);
    }

    private boolean isEmailUnique(String email) {
        return customerRepository.findByEmail(email) == null;
    }
}
