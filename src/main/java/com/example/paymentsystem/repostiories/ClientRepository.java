package com.example.paymentsystem.repostiories;

import com.example.paymentsystem.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByPassportNumber(String passportNumber);
    Optional<Client> findByPassportNumber(String passportNumber);
}
