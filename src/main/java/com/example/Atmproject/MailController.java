package com.example.Atmproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
    @Autowired
    CashWithdrawal cashWithdrawal;

    @GetMapping("/api/atm-inbox")
    public String showMails() {
        return cashWithdrawal.getMailList().toString();
    }
}
