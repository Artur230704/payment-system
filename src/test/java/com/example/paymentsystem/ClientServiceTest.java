package com.example.paymentsystem;

import com.example.paymentsystem.dtos.CardIssuanceDTO;
import com.example.paymentsystem.entities.Client;
import com.example.paymentsystem.mappers.ClientMapper;
import com.example.paymentsystem.repostiories.ClientRepository;
import com.example.paymentsystem.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClientServiceTest {
    private ClientService clientService;
    private ClientRepository clientRepository;

    @BeforeEach
    public void setUp() {
        clientRepository = mock(ClientRepository.class);
        clientService = new ClientService(clientRepository);
    }

    @Test
    public void testSaveClient() {
        CardIssuanceDTO cardIssuanceDTO = CardIssuanceDTO.builder()
                .passportNumber("1111111")
                .build();

        Client client = ClientMapper.fromDTO(cardIssuanceDTO);

        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client result = clientService.saveClient(cardIssuanceDTO);

        assertNotNull(result);
        assertEquals(client, result);
    }

    @Test
    public void testFindByPassportNumber_successful() {
        String passportNumber = "1111111";
        Client client = new Client();

        when(clientRepository.findByPassportNumber(passportNumber)).thenReturn(Optional.of(client));

        Optional<Client> result = clientService.findByPassportNumber(passportNumber);

        assertTrue(result.isPresent());
        assertEquals(client, result.get());
    }

    @Test
    public void testFindByPassportNumber_unsuccessful() {
        String passportNumber = "1111111";

        when(clientRepository.findByPassportNumber(passportNumber)).thenReturn(Optional.empty());

        Optional<Client> result = clientService.findByPassportNumber(passportNumber);

        assertFalse(result.isPresent());
    }
}
