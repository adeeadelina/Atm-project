package com.example.Atmproject.controller;

import com.example.Atmproject.service.ATMRefillService;
import com.example.Atmproject.service.ActivityHistoryServiceImpl;
import com.example.Atmproject.util.ActivityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ATMRefillController {
    @Autowired
    private ATMRefillService atmRefillService;

    @Autowired
    private ActivityHistoryServiceImpl activityHistoryService;

    @GetMapping("/api/atm-refill")
    public ResponseEntity<String> refillATM(RequestEntity<String> request) {
        atmRefillService.refillATM();
        ResponseEntity<String> response = new ResponseEntity<>("ATM successfully refilled!", HttpStatus.OK);
        ActivityEntity activity = new ActivityEntity("non-transaction", request, response);
        activityHistoryService.addActivity(activity);
        return response;
    }
}
