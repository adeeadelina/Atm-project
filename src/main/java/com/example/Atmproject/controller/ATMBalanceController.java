package com.example.Atmproject.controller;

import com.example.Atmproject.service.ATMCheckBalance;
import com.example.Atmproject.service.TransactionHistoryService;
import com.example.Atmproject.util.TransactionEntity;
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
    private TransactionHistoryService transactionHistoryService;

    @GetMapping("/api/check-balance")
    public ResponseEntity<String> checkBalance(RequestEntity<String> request) {
        ResponseEntity<String> response = new ResponseEntity<>("Your balance is: " + atmCheckBalance.calculateBalance(), HttpStatus.OK);
        TransactionEntity transaction = new TransactionEntity("non-transaction", request, response);
        transactionHistoryService.addTransaction(transaction);
        return response;
    }
}
