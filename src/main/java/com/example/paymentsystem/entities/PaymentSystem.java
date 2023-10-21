package com.example.paymentsystem.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "payment_systems")
@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "system_name")
    private String systemName;
    private double percent;
}
