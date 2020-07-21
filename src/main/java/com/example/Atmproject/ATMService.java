package com.example.Atmproject;

import java.util.Comparator;
import java.util.TreeMap;

public interface ATMService {
    TreeMap<Integer, Integer> balance = new TreeMap<>(Comparator.reverseOrder());

    boolean isAvailable(int amount);

    int calculateBalance();

    void updateBalance(int nrOfBills, int typeOfBills);

    void verifyBalance();

    void verify100WarningCase();

    void verify100CriticalCase();

    void verify50WarningCase();
}
