package com.example.paymentsystem.repostiories;

import com.example.paymentsystem.entities.Card;
import com.example.paymentsystem.entities.PaymentSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    boolean existsByCardNumber(String cardNumber);
    boolean existsByClient_PhoneNumberAndPaymentSystem(String phoneNumber, PaymentSystem paymentSystem);
    Optional<Card> findByCardNumberAndPaymentSystem(String cardNumber, PaymentSystem paymentSystem);
    List<Card> findByClientPassportNumber(String passportNumber);
}
