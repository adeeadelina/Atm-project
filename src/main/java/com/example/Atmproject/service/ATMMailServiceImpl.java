package com.example.Atmproject.service;

import com.example.Atmproject.util.MailNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ATMMailServiceImpl implements ATMMailService {
    @Autowired
    CashWithdrawalService cashWithdrawalService;

    @Override
    public ArrayList<MailNotification> getMaiList() {
        return cashWithdrawalService.getMailList();
    }
}
