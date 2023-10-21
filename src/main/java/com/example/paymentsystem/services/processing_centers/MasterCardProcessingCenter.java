package com.example.paymentsystem.services.processing_centers;

import com.example.paymentsystem.dtos.CardIssuanceDTO;
import com.example.paymentsystem.dtos.CardReplenishmentDTO;
import com.example.paymentsystem.dtos.CardWithdrawalDTO;
import com.example.paymentsystem.services.PaymentProcessingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("MasterCard")
@RequiredArgsConstructor
public class MasterCardProcessingCenter implements PaymentProcessingStrategy {
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