package com.example.Atmproject;

public class MailNotification {
    public String messageToSend;

    public MailNotification(String message) {
        this.messageToSend = message;
    }

    public void sendMail() {
        System.out.println("ATM machine" + "\n" + "To: fillMeUpPlease@superbancomat.com" + "\n" + messageToSend);
    }
}
