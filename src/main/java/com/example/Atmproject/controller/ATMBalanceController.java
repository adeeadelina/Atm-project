package com.example.Atmproject.controller;

import com.example.Atmproject.CashWithdrawal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ATMBalanceController {
    @Autowired
    CashWithdrawal cashWithdrawal;

    @GetMapping("/api/check-balance")
    public String checkBalance() {
        return "Your balance is: " + cashWithdrawal.getBalance();
    }
}