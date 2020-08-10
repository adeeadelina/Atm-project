package com.example.Atmproject.controller;

import com.example.Atmproject.clients.DianaClient;
import com.example.Atmproject.clients.DragosClient;
import com.example.Atmproject.dto.ATMResponseDTO;
import com.example.Atmproject.exception.ImpossibleSplitException;
import com.example.Atmproject.exception.IncorrectAmountException;
import com.example.Atmproject.exception.NotEnoughMoneyException;
import com.example.Atmproject.service.ActivityHistoryServiceImpl;
import com.example.Atmproject.service.CashWithdrawalService;
import com.example.Atmproject.service.TransactionHistoryServiceImpl;
import com.example.Atmproject.util.ActivityEntity;
import com.example.Atmproject.util.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ATMTransactionController {
    @Autowired
    private TransactionHistoryServiceImpl transactionHistoryService;

    @Autowired
    private ActivityHistoryServiceImpl activityHistoryService;

    @Autowired
    private CashWithdrawalService cashWithdrawalService;

    @Autowired
    private DianaClient dianaClient;

    @Autowired
    private DragosClient dragosClient;

    @GetMapping("/api/new-transaction")
    public ResponseEntity<ATMResponseDTO> cashWithdraw(@RequestParam(value = "sum", defaultValue = "0") int amount, RequestEntity<String> request)
            throws IncorrectAmountException, ImpossibleSplitException, NotEnoughMoneyException {

        try {
            return createResponse(amount, request, cashWithdrawalService.withdraw(amount));
        } catch (IncorrectAmountException incorrectAmountException) {
            throw new IncorrectAmountException();
        } catch (Exception e1) {
            try {
                return createResponse(amount, request, dianaClient.cashWithdraw(amount));
            } catch (Exception e2) {
                try {
                    return createResponse(amount, request, dragosClient.cashWithdraw(amount));

                } catch (Exception e3) {
                    return createResponse(amount, request, cashWithdrawalService.withdraw(amount));
                }
            }
        }

    }

    public ResponseEntity<ATMResponseDTO> createResponse(int amount, RequestEntity<String> request, ATMResponseDTO atmResponseDTO) {
        ResponseEntity<ATMResponseDTO> responseEntity = ResponseEntity.status(HttpStatus.OK).body(atmResponseDTO);
        ActivityEntity activity = new ActivityEntity("transaction", request, responseEntity);
        activityHistoryService.addActivity(activity);
        TransactionEntity transaction = new TransactionEntity(amount, responseEntity);
        transactionHistoryService.addTransaction(transaction);
        return responseEntity;
    }

    @RequestMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Hello!");
    }

}
