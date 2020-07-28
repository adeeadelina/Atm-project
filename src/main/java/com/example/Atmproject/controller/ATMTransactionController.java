package com.example.Atmproject.controller;

import com.example.Atmproject.ATMClient;
import com.example.Atmproject.CashWithdrawal;
import com.example.Atmproject.dto.ATMResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ATMTransactionController {

    @Autowired
    CashWithdrawal cashWithdrawal;

    @Autowired
    ATMClient atmClient;

    @GetMapping("/api/new-transaction")
    public ResponseEntity<ATMResponseDTO> cashWithdraw(@RequestParam(value = "sum", defaultValue = "0") int amount) {
        // TODO I don't have enough money (withdraw doesn't return 'Total
        // number of bills, then ask for money from Diana, then Dragos.
        // else case returns 'Cannot withdraw money'.
        ATMResponseDTO response;
        if ((response = cashWithdrawal.withdraw(amount)).getResponseMessage().equals("Transaction denied")) {
           // return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(atmClient.cashWithdraw(amount), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Hello!");
    }

}
