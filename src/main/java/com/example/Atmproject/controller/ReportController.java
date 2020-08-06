package com.example.Atmproject.controller;

import com.example.Atmproject.pdf.PDFGenerator;
import com.example.Atmproject.service.ActivityHistoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {
    @Autowired
    private ActivityHistoryServiceImpl activityHistoryService;

    @GetMapping("/api/report")
    public ResponseEntity<String> getReport() throws Exception {
        PDFGenerator pdfGenerator = new PDFGenerator(activityHistoryService.createTable());
        return new ResponseEntity<>("Report successfully created!", HttpStatus.OK);
    }

    @GetMapping("/api/report/{mins}")
    public ResponseEntity<String> getReportByTime(@PathVariable("mins") int mins) throws Exception {
        PDFGenerator pdfGenerator = new PDFGenerator((activityHistoryService.createFilteredTable(mins)));
        return new ResponseEntity<>("Report successfully created!", HttpStatus.OK);
    }
}
