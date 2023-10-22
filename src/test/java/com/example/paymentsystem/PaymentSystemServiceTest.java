package com.example.paymentsystem;

import com.example.paymentsystem.entities.PaymentSystem;
import com.example.paymentsystem.exceptions.PaymentSystemNotFoundException;
import com.example.paymentsystem.repostiories.PaymentSystemRepository;
import com.example.paymentsystem.services.PaymentSystemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PaymentSystemServiceTest {
    private PaymentSystemService paymentSystemService;
    private PaymentSystemRepository paymentSystemRepository;

    @BeforeEach
    public void setUp() {
        paymentSystemRepository = mock(PaymentSystemRepository.class);
        paymentSystemService = new PaymentSystemService(paymentSystemRepository);
    }

    @Test
    public void testFindBySystemName_successful() {
        String systemName = "Visa";
        PaymentSystem paymentSystem = new PaymentSystem();

        when(paymentSystemRepository.findBySystemNameIgnoreCase(systemName)).thenReturn(Optional.of(paymentSystem));

        PaymentSystem result = paymentSystemService.findBySystemName(systemName);

        assertEquals(paymentSystem, result);
    }

    @Test
    public void testFindBySystemName_unsuccessful() {
        String systemName = "Elcart";
        when(paymentSystemRepository.findBySystemNameIgnoreCase(systemName)).thenReturn(Optional.empty());

        assertThrows(PaymentSystemNotFoundException.class, () -> paymentSystemService.findBySystemName(systemName));
    }
}
