package com.example.Atmproject.exception;

public class IncorrectAmountException extends Exception {
    public IncorrectAmountException() {
        super("Cannot withdraw money.");
    }
}
