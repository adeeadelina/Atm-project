package com.example.Atmproject.util;

import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class TransactionEntity {
    private final LocalDateTime time;
    private final int sum;
    private final ResponseEntity<?> response;

    public TransactionEntity(int sum, ResponseEntity<?> response) {
        this.time = LocalDateTime.now();
        this.sum = sum;
        this.response = response;
    }


    public LocalDateTime getTime() {
        return time;
    }

    public int getSum() {
        return sum;
    }

    public ResponseEntity<?> getResponse() {
        return response;
    }

    public String getTimeFormatPDF() {
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String getRequestFormatPDF() {
        return "Sum: " + sum;
    }

    public String getResponseFormatPDF() {
        return response.getStatusCodeValue() + " : " +  Objects.requireNonNull(response.getBody()).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionEntity that = (TransactionEntity) o;
        return sum == that.sum &&
                time.equals(that.time) &&
                response.equals(that.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, sum, response);
    }

    public int compareTo(TransactionEntity o1) {
        if (this.time.isAfter(o1.time)) {
            return 1;
        } else if(this.time.isBefore(o1.time)){
            return -1;
        } else {
            return 0;
        }
    }
}
