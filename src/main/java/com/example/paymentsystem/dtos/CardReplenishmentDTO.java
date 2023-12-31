package com.example.paymentsystem.dtos;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CardReplenishmentDTO {
    @NotNull(message = "Введите название платежной системы")
    private String paymentSystem;
    @NotNull(message = "Введите номер карты")
    private String cardNumber;
    @Min(value = 20, message = "Минимальная сумма для пополения 20 сом")
    private double amount;
}
