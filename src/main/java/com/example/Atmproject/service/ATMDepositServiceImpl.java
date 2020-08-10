package com.example.Atmproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ATMDepositServiceImpl {
    @Autowired
    private ATMService atmMachine;

    public void depositCash(int amount) {
        int[] bills = {100, 50, 10, 5, 1};
        while (amount != 0) {
            for (int bill : bills) {
                if (amount > bill) {
                    int nrOfBillsToDeposit = amount / bill;
                    atmMachine.updateBalance("+", nrOfBillsToDeposit, bill);
                    amount = amount % bill;
                }
            }
        }
    }
}
