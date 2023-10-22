package com.example.paymentsystem.exceptions;

public class CardAlreadyIssuedException extends RuntimeException {
    public CardAlreadyIssuedException(String message) {
        super(message);
    }
}

