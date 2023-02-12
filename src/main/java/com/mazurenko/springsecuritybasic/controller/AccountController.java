package com.mazurenko.springsecuritybasic.controller;

import com.mazurenko.springsecuritybasic.entity.Accounts;
import com.mazurenko.springsecuritybasic.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private AccountsRepository accountsRepository;

    @Autowired
    public AccountController(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @GetMapping("/myAccount")
    @PostAuthorize("returnObject?.customer?.email == authentication.name")
    public Accounts getAccountDetails(@RequestParam Long id) {
        return accountsRepository.findByCustomerId(id);
    }
}
