package com.example.Atmproject.controller;

import com.example.Atmproject.service.ActivityHistoryService;
import com.example.Atmproject.util.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AvailableController {
    @Autowired
    private ActivityHistoryService activityHistoryService;

    @GetMapping("/api/online")
    public ResponseEntity<String> availableResponse(RequestEntity<String> request) {
        try {
            ResponseEntity<String> response = new ResponseEntity<>("OK", HttpStatus.OK);
            TransactionEntity transaction = new TransactionEntity("non-transaction", request, response);
            activityHistoryService.addTransaction(transaction);
            return response;
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
