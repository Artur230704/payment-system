package com.example.paymentsystem.dtos;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CardWithdrawalDTO {
    @NotNull(message = "Введите название платежной системы")
    private String paymentSystem;
    @NotNull(message = "Введите номер карты")
    private String cardNumber;
    @NotNull(message = "Введите cvv")
    private String pinCode;
    @Min(value = 50, message = "Минимальная сумма для снятия 50 сом")
    private double amount;
}
