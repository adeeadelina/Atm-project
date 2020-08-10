package com.example.Atmproject.service;

import com.example.Atmproject.util.MailNotification;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

public interface ATMService {
    TreeMap<Integer, Integer> balance = new TreeMap<>(Comparator.reverseOrder());

    boolean isAvailable(int amount);

    void fillATM();

    void updateBalance(String operation, int nrOfBills, int typeOfBills);

    ArrayList<MailNotification> verifyBalance();

    MailNotification verify100WarningCase();

    MailNotification verify100CriticalCase();

    MailNotification verify50WarningCase();
}
