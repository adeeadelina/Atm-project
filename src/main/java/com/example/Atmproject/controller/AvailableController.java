package com.example.Atmproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AvailableController {
    @GetMapping("/api/online")
    public ResponseEntity<String> availableResponse() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
