package com.example.paymentsystem.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "log_entries")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "client_passport_number")
    private String clientPassportNumber;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "payment_system_name")
    private String paymentSystemName;
    private String action;
    private LocalDate date;
    private String response;
}
