package com.example.Atmproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ATMController {

    @Autowired
    CashWithdrawal cashWithdrawal;

    @GetMapping("/api/atm")
    public String cashWithdraw(@RequestParam(defaultValue = "0") int amount) {
        return cashWithdrawal.withdraw(amount);
    }

    @RequestMapping("/")
    public String home() {
        return "Hello!";
    }

}
