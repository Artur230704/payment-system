package com.example.paymentsystem.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientDTO {
    private Long id;
    private String passportNumber;
    private String firstName;
    private String lastName;
    private List<CardDTO> cards;
}
