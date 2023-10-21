package com.example.paymentsystem.repostiories;

import com.example.paymentsystem.entities.PaymentSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentSystemRepository extends JpaRepository<PaymentSystem, Long> {
}
