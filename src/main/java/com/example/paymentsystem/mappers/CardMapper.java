package com.example.paymentsystem.mappers;

import com.example.paymentsystem.dtos.CardDTO;
import com.example.paymentsystem.entities.Card;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {
    public static CardDTO fromCard(Card card) {
        return CardDTO.builder()
                .id(card.getId())
                .clientId(card.getClient().getId())
                .cardNumber(card.getCardNumber())
                .phoneNumber(card.getPhoneNumber())
                .expirationDate(card.getExpirationDate())
                .balance(card.getBalance())
                .paymentSystemName(card.getPaymentSystem().getSystemName())
                .build();
    }
}
