package com.example.paymentsystem.repostiories;

import com.example.paymentsystem.entities.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {
}
