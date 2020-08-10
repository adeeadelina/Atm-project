package com.example.Atmproject.service;

import com.example.Atmproject.util.MailNotification;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
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

    @Override
    public Table createTable() {
        float[] pointColumnWidths = {150, 400};
        Table table = new com.itextpdf.layout.element.Table(pointColumnWidths);

        for (MailNotification mail : cashWithdrawalService.getMailList()) {
            table.addCell(new Cell().add(mail.getTime()));
            table.addCell(new Cell().add(mail.toString()));
        }

        return table;
    }
}
