package com.example.Atmproject;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class ATMServiceImpl implements ATMService {
    // key - type of bill, value - how many bills of that type
    public ATMServiceImpl() {
        this.fillATM();
    }

    public void fillATM() {
        balance.put(1, 100);
        balance.put(5, 100);
        balance.put(10, 100);
        balance.put(50, 50);
        balance.put(100, 50);
    }

    public boolean isAvailable(int amount) {
        return amount <= calculateBalance();
    }

    public int calculateBalance() {
        int currentBalance = 0;
        for (Map.Entry<Integer, Integer> entry : balance.entrySet()) {
            currentBalance += entry.getKey() * entry.getValue();
        }
        return currentBalance;
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
