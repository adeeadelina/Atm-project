package com.example.Atmproject.exception;

public class ImpossibleSplitException extends Exception {
    public ImpossibleSplitException() {
        super("Cannot split into bills.");
    }
}
