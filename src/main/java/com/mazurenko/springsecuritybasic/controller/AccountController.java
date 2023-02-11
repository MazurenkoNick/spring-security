package com.mazurenko.springsecuritybasic.controller;

import com.mazurenko.springsecuritybasic.entity.Accounts;
import com.mazurenko.springsecuritybasic.repository.AccountsRepository;
import com.mazurenko.springsecuritybasic.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private AccountsRepository accountsRepository;

    public AccountController(CustomerService customerService, AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(@RequestParam Long id) {
        Accounts account = accountsRepository.findByCustomerId(id);
        return account;
    }
}
