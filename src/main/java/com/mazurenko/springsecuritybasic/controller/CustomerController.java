package com.mazurenko.springsecuritybasic.controller;

import com.mazurenko.springsecuritybasic.entity.Customer;
import com.mazurenko.springsecuritybasic.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        return customerService.findByEmail(authentication.getName());
    }

    @GetMapping("/register")
    public ResponseEntity<String> registerCustomer(@Valid @RequestBody Customer customer) {
        ResponseEntity<String> response;

        try {
            customerService.register(customer);
            response = ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("User has been successfully created");
        }
        catch (Exception e) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
        return response;
    }
}
