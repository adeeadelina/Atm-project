package com.example.Atmproject.util;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class TransactionEntity {
    private final LocalDateTime time;

    // "transaction" / "non-transaction"
    private final String type;
    private final RequestEntity<?> request;
    private final ResponseEntity<?> response;

    public TransactionEntity(String type, RequestEntity<?> request, ResponseEntity<?> response) {
        this.time = LocalDateTime.now();
        this.type = type;
        this.request = request;
        this.response = response;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public RequestEntity<?> getRequest() {
        return request;
    }

    public ResponseEntity<?> getResponse() {
        return response;
    }

    public String getTimeFormatPDF() {
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String getRequestFormatPDF() {
        return request.getMethod() + " : " + request.getUrl();
    }

    // TODO the sum I need to withdraw must appear
    public String getTransactionRequestFormatPDF() {
        return request.getMethod() + " : " + request.getUrl();
    }

    public String getResponseFormatPDF() {
        return response.getStatusCodeValue() + " : " + response.getBody();
    }

    public String getTransactionResponseFormatPDF() {
        return response.getStatusCodeValue() + " : " + Objects.requireNonNull(response.getBody()).toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionEntity that = (TransactionEntity) o;
        return time.equals(that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time);
    }

    public int compareTo(TransactionEntity o1) {
        if (this.equals(o1)) {
            return 0;
        } else if (this.time.isAfter(o1.time)) {
            return 1;
        } else {
            return -1;
        }
    }
}
