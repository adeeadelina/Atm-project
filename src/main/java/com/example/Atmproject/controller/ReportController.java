package com.example.Atmproject.controller;

import com.example.Atmproject.pdf.PDFGenerator;
import com.example.Atmproject.service.ActivityHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {
    @Autowired
    private ActivityHistoryService activityHistoryService;

    @GetMapping("/api/report")
    public ResponseEntity<String> getReport() throws Exception {
        PDFGenerator pdfGenerator = new PDFGenerator(activityHistoryService.createTransactionsTable());
        return new ResponseEntity<>("Report successfully created!", HttpStatus.OK);
    }

    @GetMapping("/api/report/{mins}")
    public ResponseEntity<String> getReportByTime(@PathVariable("mins") int mins) throws Exception {
        PDFGenerator pdfGenerator = new PDFGenerator((activityHistoryService.createFilteredTable(mins)));
        return new ResponseEntity<>("Report successfully created!", HttpStatus.OK);
    }
}
