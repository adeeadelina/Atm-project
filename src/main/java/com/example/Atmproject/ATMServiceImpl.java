package com.example.Atmproject;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ATMServiceImpl implements ATMService {
    // descending order
    // lambda function to compare
//    TreeMap<Integer, Integer> balance = new TreeMap<>((o1, o2) -> o2.compareTo(o1));

    // key - type of bill, value - how many bills of that type
    public ATMServiceImpl() {
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
    public void verifyBalance() {
        int keyToRemove = 0;
        for (Map.Entry<Integer, Integer> entry : balance.entrySet()) {
            if (entry.getValue() == 0) {
                keyToRemove = entry.getKey();
            }
        }
        balance.remove(keyToRemove);
        if (balance.containsKey(100)) {
            verify100WarningCase();
            verify100CriticalCase();
        }
        if (balance.containsKey(50)) {
            verify50WarningCase();
        }
    }

    public void verify100WarningCase() {
        if (balance.get(100) < 10 && balance.get(100) >= 5) {
            MailNotification notification = new MailNotification("WARNING: Number of 100 bills under 20%");
            notification.sendMail();
        }
    }

    public void verify100CriticalCase() {
        if (balance.get(100) < 5) {
            MailNotification notification = new MailNotification("CRITICAL: Number of 100 bills under 10%");
            notification.sendMail();
        }
    }

    public void verify50WarningCase() {
        if (balance.get(50) < 8) {
            MailNotification notification = new MailNotification("WARNING: Number of 50 bills under 15%");
            notification.sendMail();
        }
    }

}
