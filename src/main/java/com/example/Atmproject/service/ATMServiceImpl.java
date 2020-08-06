package com.example.Atmproject.service;

import com.example.Atmproject.util.MailNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

@Service
public class ATMServiceImpl implements ATMService {
    @Autowired
    private final ATMCheckBalance atmCheckBalance;

    // key - type of bill, value - how many bills of that type
    public ATMServiceImpl(ATMCheckBalance atmCheckBalance) {
        this.fillATM();
        this.atmCheckBalance = atmCheckBalance;
    }

    public void fillATM() {
        balance.put(1, 100);
        balance.put(5, 100);
        balance.put(10, 100);
        balance.put(50, 50);
        balance.put(100, 50);
    }

    public boolean isAvailable(int amount) {
        return amount <= atmCheckBalance.calculateBalance();
    }

    public void updateBalance(int nrOfBills, int typeOfBills) {
        balance.put(typeOfBills, balance.get(typeOfBills) - nrOfBills);

    }

    // if there are types with no bills left, erase them
    // identify warning and critical cases
    public ArrayList<MailNotification> verifyBalance() {
        ArrayList<MailNotification> listToReturn = new ArrayList<>();
        int keyToRemove = 0;
        for (Map.Entry<Integer, Integer> entry : balance.entrySet()) {
            if (entry.getValue() == 0) {
                keyToRemove = entry.getKey();
            }
        }
        balance.remove(keyToRemove);
        if (balance.containsKey(100)) {
            listToReturn.add(verify100WarningCase());
            listToReturn.add(verify100CriticalCase());
        }
        if (balance.containsKey(50)) {
            listToReturn.add(verify50WarningCase());
        }
        listToReturn.removeIf(Objects::isNull);
        return listToReturn;
    }

    public MailNotification verify100WarningCase() {
        if (balance.get(100) < 10 && balance.get(100) >= 5) {
            return new MailNotification("WARNING: Number of 100 bills under 20%");
        }
        return null;
    }

    public MailNotification verify100CriticalCase() {
        if (balance.get(100) < 5) {
            return new MailNotification("CRITICAL: Number of 100 bills under 10%");
        }
        return null;
    }

    public MailNotification verify50WarningCase() {
        if (balance.get(50) < 8) {
            return new MailNotification("WARNING: Number of 50 bills under 15%");
        }
        return null;
    }

}
