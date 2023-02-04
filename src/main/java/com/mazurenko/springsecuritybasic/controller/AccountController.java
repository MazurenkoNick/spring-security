package com.mazurenko.springsecuritybasic.controller;

import com.mazurenko.springsecuritybasic.entity.Customer;
import com.mazurenko.springsecuritybasic.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    CustomerService customerService;

    @Autowired
    public AccountController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/myAccount")
    public String getAccountDetails() {
        return "account";
    }

    @GetMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody Customer customer) {
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
