package com.example.Atmproject.clients;

import com.example.Atmproject.dto.ATMResponseDTO;
import org.springframework.http.ResponseEntity;

public interface ATMClient {
    ATMResponseDTO cashWithdraw(int amount);

    ResponseEntity<String> isOnline();
}
