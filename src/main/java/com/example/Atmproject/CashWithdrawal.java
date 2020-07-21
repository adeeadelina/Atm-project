package com.example.Atmproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;

@Service
public class CashWithdrawal {
    @Autowired
    private ATMService atmMachine;

    public String withdraw(int amount) {
        if (!isAmountCorrect(amount) || !atmMachine.isAvailable(amount)) {
            return "Cannot withdraw money.";
        }
        HashSet<Bills> billsReturned = new HashSet<>();
        int nrOfBills = 0, typeOfBills, totalBills = 0;
        while (amount != 0) {
            for (Map.Entry<Integer, Integer> entry : atmMachine.balance.entrySet()) {
                if (entry.getKey() <= amount) {
                    nrOfBills = amount / entry.getKey();
                    typeOfBills = entry.getKey();
                    if (nrOfBills > entry.getValue()) {
                        nrOfBills = entry.getValue();
                    }
                    totalBills += nrOfBills;
                    amount -= typeOfBills * nrOfBills;
                    if (nrOfBills != 0) {
                        Bills goodBills = new Bills(nrOfBills, typeOfBills);
                        billsReturned.add(goodBills);
                    }
                    atmMachine.updateBalance(nrOfBills, typeOfBills);
                }
            }
            // when the split into bills cannot be done
            if (nrOfBills == 0) {
                return "Cannot withdraw money.";
            }
            atmMachine.verifyBalance();

        }
        return "Total number of bills used: " + totalBills + ": " + billsReturned.toString();
    }

    //checks if the amount is a valid number for such request
    public boolean isAmountCorrect(int amount) {
        return amount > 0;
    }
}
