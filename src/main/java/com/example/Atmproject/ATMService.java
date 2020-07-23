package com.example.Atmproject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

public interface ATMService {
    TreeMap<Integer, Integer> balance = new TreeMap<>(Comparator.reverseOrder());

    boolean isAvailable(int amount);

    void fillATM();

    int calculateBalance();

    void updateBalance(int nrOfBills, int typeOfBills);

    ArrayList<MailNotification> verifyBalance();

    MailNotification verify100WarningCase();

    MailNotification verify100CriticalCase();

    MailNotification verify50WarningCase();
}
