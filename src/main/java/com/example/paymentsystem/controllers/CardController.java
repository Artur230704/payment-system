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
    public ResponseEntity<Boolean> issueCard(@Valid @RequestBody CardIssuanceDTO cardIssuanceDTO) {
        return new ResponseEntity<>(cardService.issueCard(cardIssuanceDTO), HttpStatus.OK);
    }

    @PostMapping("/api/cards/replenish")
    public ResponseEntity<Boolean> replenishBalance(@Valid @RequestBody CardReplenishmentDTO cardReplenishmentDTO) {
        return new ResponseEntity<>(cardService.replenishBalance(cardReplenishmentDTO), HttpStatus.OK);
    }

    @PostMapping("/api/cards/withdraw")
    public ResponseEntity<Boolean> withdrawFunds(@Valid @RequestBody CardWithdrawalDTO cardWithdrawalDTO) {
        return new ResponseEntity<>(cardService.withdrawFunds(cardWithdrawalDTO), HttpStatus.OK);
    }
}
