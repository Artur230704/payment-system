package com.example.paymentsystem.utils;

import com.example.paymentsystem.dtos.CardIssuanceDTO;
import com.example.paymentsystem.dtos.CardWithdrawalDTO;
import com.example.paymentsystem.entities.Card;
import com.example.paymentsystem.entities.PaymentSystem;
import com.example.paymentsystem.repostiories.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class CardUtil {
    private final CardRepository cardRepository;
    public boolean isCardIssuedForPhoneNumber(CardIssuanceDTO cardIssuanceDTO, PaymentSystem paymentSystem) {
        return cardRepository.existsByPhoneNumberAndPaymentSystem(cardIssuanceDTO.getPhoneNumber(), paymentSystem);
    }

    public boolean withdrawIsAvailable(CardWithdrawalDTO cardWithdrawalDTO, Card card) {
        if (!card.getPinCode().equals(cardWithdrawalDTO.getPinCode())) {
            return false;
        }
        if (card.getBalance() < cardWithdrawalDTO.getAmount()) {
            return false;
        }
        return true;
    }

    public String generateUniqueCardNumber(String format, PaymentSystem paymentSystem) {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder(format);

        boolean unique = false;
        while (!unique) {
            for (int i = format.length(); i < 16; i++) {
                int digit = random.nextInt(10);
                cardNumber.append(digit);
            }

            unique = !cardRepository.existsByCardNumberAndAndPaymentSystem(cardNumber.toString(), paymentSystem);
            if (!unique) {
                cardNumber.setLength(format.length());
            }
        }

        return cardNumber.toString();
    }

    public String generatePinCode() {
        Random random = new Random();
        StringBuilder pinCode = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            int digit = random.nextInt(10);
            pinCode.append(digit);
        }

        return pinCode.toString();
    }
}
