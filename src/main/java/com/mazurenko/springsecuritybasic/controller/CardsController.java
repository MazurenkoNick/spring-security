package com.mazurenko.springsecuritybasic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CardsController {

    @GetMapping("/myCards")
    public String getCardDetails() {
        return "card";
    }
}
