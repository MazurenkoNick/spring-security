package com.mazurenko.springsecuritybasic.controller;

import com.mazurenko.springsecuritybasic.entity.Accounts;
import com.mazurenko.springsecuritybasic.entity.Customer;
import com.mazurenko.springsecuritybasic.repository.AccountsRepository;
import com.mazurenko.springsecuritybasic.repository.CustomerRepository;
import com.mazurenko.springsecuritybasic.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private CustomerService customerService;
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    public AccountController(CustomerService customerService, AccountsRepository accountsRepository, CustomerRepository customerRepository) {
        this.customerService = customerService;
        this.accountsRepository = accountsRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(@RequestParam Long id) {
        Accounts account = accountsRepository.findByCustomerId(id);
        return account;
    }

    @GetMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        return customerRepository.findByEmail(authentication.getName());
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
