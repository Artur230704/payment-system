package com.example.paymentsystem.dtos;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class CardIssuanceDTO {
    @NotNull(message = "Введите название платежной системы")
    private String paymentSystem;
    @NotNull(message = "Введите имя клиента")
    private String passportNumber;
    @NotNull(message = "Введите имя клиента")
    private String firstName;
    @NotNull(message = "Введите фамилию клиента")
    private String lastName;
    @Pattern(regexp = "^(\\+996\\s?)?(\\d{3}\\s?)?\\d{6}$", message = "Введите номер телефона в формате: +996000000000")
    private String phoneNumber;
}
