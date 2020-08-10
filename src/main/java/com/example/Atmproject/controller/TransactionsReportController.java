package com.example.Atmproject.controller;

import com.example.Atmproject.pdf.PDFGenerator;
import com.example.Atmproject.service.TransactionHistoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionsReportController {
    @Autowired
    private TransactionHistoryServiceImpl transactionHistoryService;

    @GetMapping("/api/report/transactions")
    public ResponseEntity<String> getReport() throws Exception {
        PDFGenerator pdfGenerator = new PDFGenerator("report", transactionHistoryService.createTable());
        return new ResponseEntity<>("Report successfully created!", HttpStatus.OK);
    }

    @GetMapping("/api/report/transactions/{mins}")
    public ResponseEntity<String> getReportByTime(@PathVariable("mins") int mins) throws Exception {
        PDFGenerator pdfGenerator = new PDFGenerator("report", transactionHistoryService.createFilteredTable(mins));
        return new ResponseEntity<>("Report successfully created!", HttpStatus.OK);
    }
}
