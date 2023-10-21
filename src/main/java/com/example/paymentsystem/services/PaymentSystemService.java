package com.example.paymentsystem.services;

import com.example.paymentsystem.repostiories.PaymentSystemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentSystemService {
    private final PaymentSystemRepository paymentSystemRepository;
}
