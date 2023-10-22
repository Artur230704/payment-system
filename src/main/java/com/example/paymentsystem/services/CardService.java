package com.example.paymentsystem.services;

import com.example.paymentsystem.dtos.CardIssuanceDTO;
import com.example.paymentsystem.dtos.CardReplenishmentDTO;
import com.example.paymentsystem.dtos.CardWithdrawalDTO;
import com.example.paymentsystem.services.processing_centers.PaymentProcessingFactory;
import com.example.paymentsystem.services.processing_centers.PaymentProcessingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {
    private final PaymentProcessingFactory paymentProcessingFactory;

    public String issueCard(CardIssuanceDTO cardIssuanceDTO) {
        PaymentProcessingStrategy paymentProcessingStrategy = paymentProcessingFactory.getPaymentStrategy(cardIssuanceDTO.getPaymentSystem());
        return paymentProcessingStrategy.issueCard(cardIssuanceDTO);
    }


    public String replenishBalance(CardReplenishmentDTO replenishmentDTO) {
        PaymentProcessingStrategy paymentProcessingStrategy = paymentProcessingFactory.getPaymentStrategy(replenishmentDTO.getPaymentSystem());
        return paymentProcessingStrategy.replenishBalance(replenishmentDTO);
    }

    public String withdrawFunds(CardWithdrawalDTO cardWithdrawalDTO) {
        PaymentProcessingStrategy paymentProcessingStrategy = paymentProcessingFactory.getPaymentStrategy(cardWithdrawalDTO.getPaymentSystem());
        return paymentProcessingStrategy.withdrawFunds(cardWithdrawalDTO);
    }
}
