package com.example.paymentsystem.services;

import com.example.paymentsystem.dtos.CardIssuanceDTO;
import com.example.paymentsystem.dtos.CardReplenishmentDTO;
import com.example.paymentsystem.dtos.CardWithdrawalDTO;

public interface PaymentProcessingStrategy {
    String issueCard(CardIssuanceDTO cardIssuanceDTO);
    String replenishBalance(CardReplenishmentDTO cardReplenishmentDTO);
    String withdrawFunds(CardWithdrawalDTO cardWithdrawalDTO);
}
