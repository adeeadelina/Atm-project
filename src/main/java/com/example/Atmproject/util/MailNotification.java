package com.example.Atmproject.util;

public class MailNotification {
    private final String messageToSend;

    public MailNotification(String message) {
        this.messageToSend = "ATM machine" + "\n" + "To: fillMeUpPlease@superbancomat.com" + "\n" + message;
    }

    public String getMessageToSend() {
        return messageToSend;
    }

    public String toString() {
        return messageToSend;
    }
}
