package com.example.paymentsystem.services;

import com.example.paymentsystem.entities.LogEntry;
import com.example.paymentsystem.repostiories.LogEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class LogEntryService {
    private final LogEntryRepository logEntryRepository;

    public void saveLogEntry(String clientPassportNumber, String cardNumber, String paymentSystemName, String action, String response) {
        LogEntry logEntry = LogEntry.builder()
                .clientPassportNumber(clientPassportNumber)
                .cardNumber(cardNumber)
                .paymentSystemName(paymentSystemName)
                .action(action)
                .date(LocalDate.now())
                .response(response)
                .build();
        logEntryRepository.save(logEntry);
    }
}
