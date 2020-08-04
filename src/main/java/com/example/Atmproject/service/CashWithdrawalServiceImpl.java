package com.example.Atmproject.service;

import com.example.Atmproject.dto.ATMResponseDTO;
import com.example.Atmproject.exception.ImpossibleSplitException;
import com.example.Atmproject.exception.IncorrectAmountException;
import com.example.Atmproject.exception.NotEnoughMoneyException;
import com.example.Atmproject.util.MailNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

@Service
public class CashWithdrawalServiceImpl implements CashWithdrawalService {
    @Autowired
    private ATMService atmMachine;

    private final ArrayList<MailNotification> mailList = new ArrayList<>();

    public ArrayList<MailNotification> getMailList() {
        return mailList;
    }

    public CashWithdrawalServiceImpl(ATMService atmService) {
        this.atmMachine = atmService;
    }

    public ATMService getAtmMachine() {
        return atmMachine;
    }
    public ATMResponseDTO withdraw(int amount) throws IncorrectAmountException, NotEnoughMoneyException, ImpossibleSplitException {
        if (!isAmountCorrect(amount)) {
            throw new IncorrectAmountException();
        } else if (!atmMachine.isAvailable(amount)) {
            throw new NotEnoughMoneyException();
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
            if (nrOfBills == 0) {
                throw new ImpossibleSplitException();
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

}
