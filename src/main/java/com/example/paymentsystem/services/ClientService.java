package com.example.paymentsystem.services;

import com.example.paymentsystem.dtos.CardIssuanceDTO;
import com.example.paymentsystem.entities.Client;
import com.example.paymentsystem.exceptions.ClientNotFoundException;
import com.example.paymentsystem.mappers.ClientMapper;
import com.example.paymentsystem.repostiories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    public Client saveClient(CardIssuanceDTO cardIssuanceDTO) {
        Client client = ClientMapper.fromDTO(cardIssuanceDTO);
        clientRepository.save(client);
        return client;
    }

    public Optional<Client> findByPassportNumber(String passportNumber) {
        return clientRepository.findByPassportNumber(passportNumber);
    }

    public Client findClientByPassportNumber(String passportNumber) {
        return clientRepository.findByPassportNumber(passportNumber)
                .orElseThrow(() -> new ClientNotFoundException("Клиент не найден"));
    }
}
