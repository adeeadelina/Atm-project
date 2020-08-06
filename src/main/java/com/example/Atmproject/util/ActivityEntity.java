package com.example.Atmproject.util;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ActivityEntity {
    private final LocalDateTime time;

    // "transaction" / "non-transaction"
    private final String type;
    private final RequestEntity<?> request;
    private final ResponseEntity<?> response;

    public ActivityEntity(String type, RequestEntity<?> request, ResponseEntity<?> response) {
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
        ActivityEntity that = (ActivityEntity) o;
        return time.equals(that.time) &&
                type.equals(that.type) &&
                request.equals(that.request) &&
                response.equals(that.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, type, request, response);
    }

    public int compareTo(ActivityEntity o1) {
        if (this.time.isAfter(o1.time)) {
            return 1;
        } else if(this.time.isBefore(o1.time)){
            return -1;
        } else {
            return 0;
        }
    }
}
