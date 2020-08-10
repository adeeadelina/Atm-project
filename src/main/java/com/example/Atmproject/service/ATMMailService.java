package com.example.Atmproject.service;

import com.example.Atmproject.util.MailNotification;
import com.itextpdf.layout.element.Table;

import java.util.ArrayList;

public interface ATMMailService {
    ArrayList<MailNotification> getMaiList();

    Table createTable();
}
