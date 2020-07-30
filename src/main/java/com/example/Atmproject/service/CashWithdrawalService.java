package com.example.Atmproject.service;

import com.example.Atmproject.dto.ATMResponseDTO;
import com.example.Atmproject.exception.ImpossibleSplitException;
import com.example.Atmproject.exception.IncorrectAmountException;
import com.example.Atmproject.exception.NotEnoughMoneyException;
import com.example.Atmproject.util.MailNotification;

import java.util.ArrayList;

public interface CashWithdrawalService {
    ArrayList<MailNotification> getMailList();

    ATMResponseDTO withdraw(int amount) throws IncorrectAmountException, NotEnoughMoneyException, ImpossibleSplitException;

    void updateMailList(ArrayList<MailNotification> mails);

    boolean isAmountCorrect(int amount);
}
