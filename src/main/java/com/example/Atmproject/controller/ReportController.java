package com.example.Atmproject.controller;

import com.example.Atmproject.pdf.PDFGenerator;
import com.example.Atmproject.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {
    @Autowired
    private TransactionHistoryService transactionHistoryService;

    @GetMapping("/api/report")
    public ResponseEntity<String> getReport() throws Exception {
        PDFGenerator pdfGenerator = new PDFGenerator(transactionHistoryService.createTransactionsTable());
        return new ResponseEntity<>("Report successfully created!", HttpStatus.OK);
    }
}
