package com.example.Atmproject.controller;

import com.example.Atmproject.pdf.PDFGenerator;
import com.example.Atmproject.service.ATMMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
    @Autowired
    private ATMMailService atmMailService;

    @GetMapping("/api/atm-inbox")
    public ResponseEntity<String > showMails() throws Exception {
        PDFGenerator pdfGenerator = new PDFGenerator("mail", atmMailService.createTable());
        return new ResponseEntity<>("Check sample.pdf for your inbox!", HttpStatus.OK);
    }
}
