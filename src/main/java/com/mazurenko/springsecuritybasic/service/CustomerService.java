package com.mazurenko.springsecuritybasic.service;

import com.mazurenko.springsecuritybasic.entity.Customer;
import com.mazurenko.springsecuritybasic.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    CustomerRepository customerRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(Customer customer) {
        String uniqueEmail = customer.getEmail();

        if (!isUniqueEmail(uniqueEmail)) {
            throw new IllegalArgumentException("User with this email already exists");
        }
        // hashing password of the user before saving
        String password = customer.getPassword();
        customer.setPassword(passwordEncoder.encode(password));

        customerRepository.save(customer);
    }

    private boolean isUniqueEmail(String email) {
        return customerRepository.findByEmail(email) == null;
    }
}
