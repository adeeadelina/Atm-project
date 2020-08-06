package com.example.Atmproject.controller;

import com.example.Atmproject.service.ActivityHistoryServiceImpl;
import com.example.Atmproject.util.ActivityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AvailableController {
    @Autowired
    private ActivityHistoryServiceImpl activityHistoryService;

    @GetMapping("/api/online")
    public ResponseEntity<String> availableResponse(RequestEntity<String> request) {
        try {
            ResponseEntity<String> response = new ResponseEntity<>("OK", HttpStatus.OK);
            ActivityEntity activity = new ActivityEntity("non-transaction", request, response);
            activityHistoryService.addActivity(activity);
            return response;
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
