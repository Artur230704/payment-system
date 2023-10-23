package com.example.paymentsystem.exceptions;

public class CardIsExpiredException extends RuntimeException{
    public CardIsExpiredException(String message) {
        super(message);
    }
}
