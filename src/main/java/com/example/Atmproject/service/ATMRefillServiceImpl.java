package com.example.Atmproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ATMRefillServiceImpl implements ATMRefillService {
    @Autowired
    private ATMService atmService;

    @Override
    public void refillATM() {
        atmService.fillATM();
    }
}
