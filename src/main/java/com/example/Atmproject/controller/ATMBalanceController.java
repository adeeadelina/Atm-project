package com.example.Atmproject.controller;

import com.example.Atmproject.service.ATMCheckBalance;
import com.example.Atmproject.service.ActivityHistoryService;
import com.example.Atmproject.util.ActivityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ATMBalanceController {
    @Autowired
    private ATMCheckBalance atmCheckBalance;

    @Autowired
    private ActivityHistoryService activityHistoryService;

    @GetMapping("/api/check-balance")
    public ResponseEntity<String> checkBalance(RequestEntity<String> request) {
        ResponseEntity<String> response = new ResponseEntity<>("Your balance is: " + atmCheckBalance.calculateBalance(), HttpStatus.OK);
        ActivityEntity activity = new ActivityEntity("non-transaction", request, response);
        activityHistoryService.addActivity(activity);
        return response;
    }
}
