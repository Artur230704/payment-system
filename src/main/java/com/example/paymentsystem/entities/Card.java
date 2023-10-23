package com.example.paymentsystem.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cards")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "issue_date")
    private LocalDate issueDate;
    @Column(name = "expiration_date")
    private LocalDate expirationDate;
    private double balance;
    @Column(name = "pin_code")
    private String pinCode;
    @ManyToOne
    @JoinColumn(name = "payment_system_id")
    private PaymentSystem paymentSystem;
}
