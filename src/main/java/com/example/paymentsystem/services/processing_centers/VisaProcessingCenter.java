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
    public String issueCard(CardIssuanceDTO cardIssuanceDTO) {
        return null;
    }

    @Override
    public String replenishBalance(CardReplenishmentDTO cardReplenishmentDTO) {
        return null;
    }

    @Override
    public String withdrawFunds(CardWithdrawalDTO cardWithdrawalDTO) {
        return null;
    }
}
