package com.example.paymentsystem.controllers;

import com.example.paymentsystem.dtos.CardDTO;
import com.example.paymentsystem.dtos.CardIssuanceDTO;
import com.example.paymentsystem.dtos.CardReplenishmentDTO;
import com.example.paymentsystem.dtos.CardWithdrawalDTO;
import com.example.paymentsystem.mappers.CardMapper;
import com.example.paymentsystem.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping("/api/cards")
    public ResponseEntity<List<CardDTO>> findCardsByPhoneNumber(@RequestParam String phoneNumber) {
        List<CardDTO> cards =  cardService.findCardsByPhoneNumber(phoneNumber).stream()
                .map(CardMapper::fromCard)
                .collect(Collectors.toList());
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

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
