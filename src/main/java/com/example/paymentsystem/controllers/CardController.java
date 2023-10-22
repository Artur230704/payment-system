package com.example.paymentsystem.controllers;

import com.example.paymentsystem.dtos.CardIssuanceDTO;
import com.example.paymentsystem.dtos.CardReplenishmentDTO;
import com.example.paymentsystem.dtos.CardWithdrawalDTO;
import com.example.paymentsystem.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @PostMapping("/api/cards/issue")
    public ResponseEntity<String> issueCard(@Valid @RequestBody CardIssuanceDTO cardIssuanceDTO) {
        String response = cardService.issueCard(cardIssuanceDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/api/cards/replenish")
    public ResponseEntity<String> replenishBalance(@Valid @RequestBody CardReplenishmentDTO cardReplenishmentDTO) {
        String response = cardService.replenishBalance(cardReplenishmentDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/api/cards/withdraw")
    public ResponseEntity<String> withdrawFunds(@Valid @RequestBody CardWithdrawalDTO cardWithdrawalDTO) {
        String response = cardService.withdrawFunds(cardWithdrawalDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
