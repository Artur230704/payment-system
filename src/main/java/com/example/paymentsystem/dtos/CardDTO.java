package com.example.paymentsystem.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CardDTO {
    private Long id;
    private Long clientId;
    private String phoneNumber;
    private String cardNumber;
    private LocalDate expirationDate;
    private double balance;
    private String paymentSystemName;
}
