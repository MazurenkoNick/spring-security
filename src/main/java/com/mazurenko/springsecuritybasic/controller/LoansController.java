package com.mazurenko.springsecuritybasic.controller;

import com.mazurenko.springsecuritybasic.entity.Loans;
import com.mazurenko.springsecuritybasic.repository.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoansController {

    private LoansRepository loansRepository;

    @Autowired
    public LoansController(LoansRepository loansRepository) {
        this.loansRepository = loansRepository;
    }

    @GetMapping("/myLoans")
    public List<Loans> getLoanDetails(@RequestParam Long id) {
        return loansRepository.findByCustomerIdOrderByStartDtDesc(id);
    }
}
