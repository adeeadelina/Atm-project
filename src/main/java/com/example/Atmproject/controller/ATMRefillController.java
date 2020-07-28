package com.example.Atmproject.controller;

import com.example.Atmproject.CashWithdrawal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ATMRefillController {
    @Autowired
    CashWithdrawal cashWithdrawal;

    @GetMapping("/api/atm-refill")
    public String refillATM() {
        cashWithdrawal.refillATM();
        return "ATM successfully refilled!";
    }
}
