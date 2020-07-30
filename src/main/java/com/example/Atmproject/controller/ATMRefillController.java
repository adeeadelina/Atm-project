package com.example.Atmproject.controller;

import com.example.Atmproject.service.ATMRefillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ATMRefillController {
    @Autowired
    private ATMRefillService atmRefillService;

    @GetMapping("/api/atm-refill")
    public String refillATM() {
        atmRefillService.refillATM();
        return "ATM successfully refilled!";
    }
}
