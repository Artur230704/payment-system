package com.example.paymentsystem.controllers;

import com.example.paymentsystem.dtos.ClientDTO;
import com.example.paymentsystem.mappers.ClientMapper;
import com.example.paymentsystem.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/api/clients")
    public ResponseEntity<ClientDTO> findClientByPassportNumber(@RequestParam  String passportNumber) {
        ClientDTO clientDTO = ClientMapper.fromClient(clientService.findClientByPassportNumber(passportNumber));
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }
}
