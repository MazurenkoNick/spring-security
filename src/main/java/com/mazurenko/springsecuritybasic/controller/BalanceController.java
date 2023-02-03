package com.mazurenko.springsecuritybasic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BalanceController {

    @GetMapping("/myBalance")
    public String getBalanceDetail() {
        return "balance";
    }
}
