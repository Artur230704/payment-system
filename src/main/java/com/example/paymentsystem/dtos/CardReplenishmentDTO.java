package com.example.paymentsystem.dtos;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CardReplenishmentDTO {
    @NotNull(message = "Не указана платежная система")
    private String paymentSystem;
    @NotNull(message = "Не указан номер карты")
    private String cardNumber;
    @Min(value = 20, message = "Минимальная сумма для пополения 20 сом")
    private double amount;
}
