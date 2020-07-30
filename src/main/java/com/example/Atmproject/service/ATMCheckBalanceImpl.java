package com.example.Atmproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ATMCheckBalanceImpl implements ATMCheckBalance {

    @Autowired
    private ATMService atmService;

    public int calculateBalance() {
        int currentBalance = 0;
        for (Map.Entry<Integer, Integer> entry : atmService.balance.entrySet()) {
            currentBalance += entry.getKey() * entry.getValue();
        }
        return currentBalance;
    }
}
