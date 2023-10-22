package com.example.paymentsystem.mappers;

import com.example.paymentsystem.dtos.CardIssuanceDTO;
import com.example.paymentsystem.entities.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    // достает из dto для выпуска карт данные, нужные для создания клиента
    public static Client fromDTO(CardIssuanceDTO cardIssuanceDTO) {
        return Client.builder()
                .passportNumber(cardIssuanceDTO.getPassportNumber())
                .firstName(cardIssuanceDTO.getFirstName())
                .lastName(cardIssuanceDTO.getLastName())
                .build();
    }
}
