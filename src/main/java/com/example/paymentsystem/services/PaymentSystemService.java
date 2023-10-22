package com.example.paymentsystem.services;

import com.example.paymentsystem.entities.PaymentSystem;
import com.example.paymentsystem.exceptions.PaymentSystemNotFoundException;
import com.example.paymentsystem.repostiories.PaymentSystemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentSystemService {
    private final PaymentSystemRepository paymentSystemRepository;
    public PaymentSystem findBySystemName(String systemName) {
        return paymentSystemRepository.findBySystemNameIgnoreCase(systemName)
                .orElseThrow(() -> new PaymentSystemNotFoundException("Платежная система не найдена"));
    }
}
