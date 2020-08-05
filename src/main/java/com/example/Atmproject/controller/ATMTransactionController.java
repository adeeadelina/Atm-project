package com.example.Atmproject.controller;

import com.example.Atmproject.clients.DianaClient;
import com.example.Atmproject.clients.DragosClient;
import com.example.Atmproject.dto.ATMResponseDTO;
import com.example.Atmproject.exception.ImpossibleSplitException;
import com.example.Atmproject.exception.IncorrectAmountException;
import com.example.Atmproject.exception.NotEnoughMoneyException;
import com.example.Atmproject.service.CashWithdrawalService;
import com.example.Atmproject.service.TransactionHistoryService;
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
    private TransactionHistoryService transactionHistoryService;

    @Autowired
    private CashWithdrawalService cashWithdrawalService;

    @Autowired
    private DianaClient dianaClient;

    @Autowired
    private DragosClient dragosClient;

    @GetMapping("/api/new-transaction")
    public ResponseEntity<ATMResponseDTO> cashWithdraw(@RequestParam(value = "sum", defaultValue = "0") int amount, RequestEntity<String> request)
            throws IncorrectAmountException, ImpossibleSplitException, NotEnoughMoneyException {
        // TODO I don't have enough money (withdraw doesn't return 'Total
        // number of bills, then ask for money from Diana, then Dragos.
        // else case returns 'Cannot withdraw money'.
        try {
            ATMResponseDTO response = cashWithdrawalService.withdraw(amount);
            ResponseEntity<ATMResponseDTO> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
            TransactionEntity transaction = new TransactionEntity("transaction", request, responseEntity);
            transactionHistoryService.addTransaction(transaction);
            return responseEntity;
        } catch(IncorrectAmountException incorrectAmountException) {
            throw new IncorrectAmountException();
        } catch (Exception e1) {
            try {
                ATMResponseDTO response = dragosClient.cashWithdraw(amount);
                ResponseEntity<ATMResponseDTO> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
                TransactionEntity transaction = new TransactionEntity("transaction", request, responseEntity);
                transactionHistoryService.addTransaction(transaction);
                return responseEntity;
            } catch (Exception e2) {
                try {
                    ATMResponseDTO response = dianaClient.cashWithdraw(amount);
                    ResponseEntity<ATMResponseDTO> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
                    TransactionEntity transaction = new TransactionEntity("transaction", request, responseEntity);
                    transactionHistoryService.addTransaction(transaction);
                    return responseEntity;
                } catch (Exception e3) {
                    ATMResponseDTO response = cashWithdrawalService.withdraw(amount);
                    ResponseEntity<ATMResponseDTO> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
                    TransactionEntity transaction = new TransactionEntity("transaction",request, responseEntity);
                    transactionHistoryService.addTransaction(transaction);
                    return responseEntity;
                }
            }
        }

        // TODO could work with status code
        // System.out.println(dragosClient.isOnline().getStatusCodeValue()) ;


    }

    @RequestMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Hello!");
    }

}
