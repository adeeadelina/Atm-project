package com.example.Atmproject.controller;

import com.example.Atmproject.dto.ATMResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.TreeMap;

@RestController
public class HumanController {
    @RequestMapping(value = "/api/{textInput}")
    public ResponseEntity<?> getRequest(@PathVariable("textInput") String textInput) {
        if (textInput.matches(".*(iau|extrag|retrag|cer|scot).*")) {
            int sum = Integer.parseInt(textInput.replaceAll("[^0-9]", ""));
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create("http://localhost:8080/api/new-transaction?sum=" + sum))
                    .build();
        } else if (textInput.matches(".*(depun|pun|adaug|bag|dau).*")) {
            int sum = Integer.parseInt(textInput.replaceAll("[^0-9]", ""));
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create("http://localhost:8080/api/atm-deposit?sum=" + sum))
                    .build();
        }
        return new ResponseEntity<>(new ATMResponseDTO("ok fra", new TreeMap<>()), HttpStatus.OK);
    }
}
