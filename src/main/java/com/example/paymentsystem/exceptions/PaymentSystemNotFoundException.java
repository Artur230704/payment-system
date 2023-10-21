package com.example.paymentsystem.exceptions;

public class PaymentSystemNotFoundException extends RuntimeException {
    public PaymentSystemNotFoundException(String message) {
        super(message);
    }
}

