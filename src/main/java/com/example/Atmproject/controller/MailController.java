package com.example.Atmproject.controller;

import com.example.Atmproject.service.CashWithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
    @Autowired
    private CashWithdrawalService cashWithdrawalService;

    @GetMapping("/api/atm-inbox")
    public String showMails() {
        return cashWithdrawalService.getMailList().toString();
    }
}
