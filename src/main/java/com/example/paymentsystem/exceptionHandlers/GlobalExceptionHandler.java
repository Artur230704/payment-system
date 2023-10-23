package com.example.paymentsystem.exceptionHandlers;

import com.example.paymentsystem.exceptions.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PaymentSystemNotFoundException.class)
    public String handlerPaymentSystemNotFoundException(PaymentSystemNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> handleMethodArgumentNotValidExceptionException(MethodArgumentNotValidException e) {
        return e.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    }

    @ExceptionHandler(CardAlreadyIssuedException.class)
    public String handlerCardAlreadyIssuedException(CardAlreadyIssuedException e) {
        return e.getMessage();
    }

    @ExceptionHandler(CardNotFoundException.class)
    public String handleCardNotFoundException(CardNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(InvalidPinException.class)
    public String handleInvalidPinException(InvalidPinException e) {
        return e.getMessage();
    }

    @ExceptionHandler(NotEnoughFundsException.class)
    public String handleNotEnoughFundsException(NotEnoughFundsException e) {
        return e.getMessage();
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public String handleClientNotFoundException(ClientNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(CardIsExpiredException.class)
    public String handleCardIsExpiredException(CardIsExpiredException e) {
        return e.getMessage();
    }
}
