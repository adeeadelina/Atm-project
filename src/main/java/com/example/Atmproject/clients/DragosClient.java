package com.example.Atmproject.clients;

import com.example.Atmproject.dto.ATMResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "dragosClient", url = "${dragos.atm}")
public interface DragosClient extends ATMClient {
    @GetMapping("/api/new-transaction")
    ATMResponseDTO cashWithdraw(@RequestParam(value = "sum", defaultValue = "0") int sum);

    @GetMapping("/api/online")
    ResponseEntity<String> isOnline();
}
