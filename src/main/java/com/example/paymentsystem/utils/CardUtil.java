package com.example.paymentsystem.utils;

import com.example.paymentsystem.dtos.CardIssuanceDTO;
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

    public String generateCVV() {
        Random random = new Random();
        StringBuilder cvv = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            int digit = random.nextInt(10);
            cvv.append(digit);
        }

        return cvv.toString();
    }
}
