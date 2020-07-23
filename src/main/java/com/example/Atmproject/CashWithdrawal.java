package com.example.Atmproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

@Service
public class CashWithdrawal {
    @Autowired
    private ATMService atmMachine;

    private final ArrayList<MailNotification> mailList = new ArrayList<>();

    public ArrayList<MailNotification> getMailList() {
        return mailList;
    }

    public ATMResponseDTO withdraw(int amount) {
        if (!isAmountCorrect(amount)) {
            return new ATMResponseDTO("Transaction denied", null);
        } else if (!atmMachine.isAvailable(amount)) {
            return new ATMResponseDTO("Transaction denied", null);
        }
        TreeMap<Integer, Integer> billsReturned = new TreeMap<>();
        int nrOfBills = 0, typeOfBills;
        while (amount != 0) {
            for (Map.Entry<Integer, Integer> entry : atmMachine.balance.entrySet()) {
                if (entry.getKey() <= amount) {
                    nrOfBills = amount / entry.getKey();
                    typeOfBills = entry.getKey();
                    if (nrOfBills > entry.getValue()) {
                        nrOfBills = entry.getValue();
                    }
                    amount -= typeOfBills * nrOfBills;
                    if (nrOfBills != 0) {
                        billsReturned.put(typeOfBills, nrOfBills);
                    }
                    atmMachine.updateBalance(nrOfBills, typeOfBills);
                }
            }
            // when the split into bills cannot be done
            // Obs! It hardly gets here
            if (nrOfBills == 0) {
                return new ATMResponseDTO("Transaction denied", null);
            }
            if (atmMachine.verifyBalance() != null) {
                updateMailList(atmMachine.verifyBalance());
            }

        }

        return new ATMResponseDTO("Transaction approved", ATMResponseDTO.transformBillsToString(billsReturned));
    }

    public void updateMailList(ArrayList<MailNotification> mails) {
        mailList.addAll(mails);
    }

    //checks if the amount is a valid number for such request
    public boolean isAmountCorrect(int amount) {
        return amount > 0;
    }

    public int getBalance() {
        return atmMachine.calculateBalance();
    }

    public void refillATM() {
        atmMachine.fillATM();
    }
}
