package com.example.Atmproject;

import com.example.Atmproject.dto.ATMResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "client", url = "http://192.168.243.15:8080/")
public interface ATMClient {
    @GetMapping("/api/new-transaction")
    ATMResponseDTO cashWithdraw(@RequestParam(value = "sum", defaultValue = "0") int amount);

    @GetMapping("/available")
    int checkBalance();
}
