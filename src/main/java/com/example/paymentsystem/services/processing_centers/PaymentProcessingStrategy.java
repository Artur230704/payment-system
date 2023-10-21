package com.example.paymentsystem.services.processing_centers;

import com.example.paymentsystem.dtos.CardIssuanceDTO;
import com.example.paymentsystem.dtos.CardReplenishmentDTO;
import com.example.paymentsystem.dtos.CardWithdrawalDTO;

public interface PaymentProcessingStrategy {
    boolean issueCard(CardIssuanceDTO cardIssuanceDTO);
    boolean replenishBalance(CardReplenishmentDTO cardReplenishmentDTO);
    boolean withdrawFunds(CardWithdrawalDTO cardWithdrawalDTO);
}
