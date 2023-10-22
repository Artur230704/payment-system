package com.example.paymentsystem.mappers;

import com.example.paymentsystem.dtos.CardIssuanceDTO;
import com.example.paymentsystem.dtos.ClientDTO;
import com.example.paymentsystem.entities.Client;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

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


    public static ClientDTO fromClient(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .passportNumber(client.getPassportNumber())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .cards(client.getCards().stream()
                        .map(CardMapper::fromCard)
                        .collect(Collectors.toList()))
                .build();
    }
}
