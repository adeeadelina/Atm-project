package com.example.Atmproject.controller;

import com.example.Atmproject.service.ATMDepositServiceImpl;
import com.example.Atmproject.service.ActivityHistoryServiceImpl;
import com.example.Atmproject.service.TransactionHistoryServiceImpl;
import com.example.Atmproject.util.ActivityEntity;
import com.example.Atmproject.util.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ATMDepositController {
    @Autowired
    ATMDepositServiceImpl atmDepositService;

    @Autowired
    ActivityHistoryServiceImpl activityHistoryService;

    @Autowired
    TransactionHistoryServiceImpl transactionHistoryService;

    @RequestMapping(value = "/api/atm-deposit", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<String> depositCash(@RequestParam(value = "sum") int amount, RequestEntity<String> request) {
        atmDepositService.depositCash(amount);
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.OK).body(amount + " added to your ATM!");
        ActivityEntity activity = new ActivityEntity("non-transaction", request, responseEntity);
        activityHistoryService.addActivity(activity);
        TransactionEntity transaction = new TransactionEntity(amount, responseEntity);
        transactionHistoryService.addTransaction(transaction);
        return responseEntity;
    }
}