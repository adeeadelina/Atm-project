package com.example.Atmproject.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MailNotification {
    private final String messageToSend;
    private final LocalDateTime time;

    public MailNotification(String message) {
        this.time = LocalDateTime.now();
        this.messageToSend = "ATM machine" + "\n" + "To: fillMeUpPlease@superbancomat.com" + "\n" + message;
    }

    public String getTime() {
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String getMessageToSend() {
        return messageToSend;
    }

    public String toString() {
        return messageToSend;
    }

}
