package com.example.paymentsystem.services.processing_centers;

import com.example.paymentsystem.dtos.CardIssuanceDTO;
import com.example.paymentsystem.dtos.CardReplenishmentDTO;
import com.example.paymentsystem.dtos.CardWithdrawalDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("Visa")
@RequiredArgsConstructor
public class VisaProcessingCenter implements PaymentProcessingStrategy {
    @Override
    public boolean issueCard(CardIssuanceDTO cardIssuanceDTO) {
        return true;
    }

    @Override
    public boolean replenishBalance(CardReplenishmentDTO cardReplenishmentDTO) {
        return true;
    }

    @Override
    public boolean withdrawFunds(CardWithdrawalDTO cardWithdrawalDTO) {
        return true;
    }
}
