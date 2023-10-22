package com.example.paymentsystem.services.processing_centers;

import com.example.paymentsystem.dtos.CardIssuanceDTO;
import com.example.paymentsystem.dtos.CardReplenishmentDTO;
import com.example.paymentsystem.dtos.CardWithdrawalDTO;
import com.example.paymentsystem.entities.Card;
import com.example.paymentsystem.entities.Client;
import com.example.paymentsystem.entities.PaymentSystem;
import com.example.paymentsystem.repostiories.CardRepository;
import com.example.paymentsystem.services.ClientService;
import com.example.paymentsystem.services.PaymentSystemService;
import com.example.paymentsystem.utils.CardUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service("MasterCard")
@RequiredArgsConstructor
public class MasterCardProcessingCenter implements PaymentProcessingStrategy {
    private final PaymentSystemService paymentSystemService;
    private final ClientService clientService;
    private final CardRepository cardRepository;
    private final CardUtil cardUtil;
    @Override
    public boolean issueCard(CardIssuanceDTO cardIssuanceDTO) {
        PaymentSystem paymentSystem = paymentSystemService.findBySystemName(cardIssuanceDTO.getPaymentSystem());
        if (cardUtil.isCardIssuedForPhoneNumber(cardIssuanceDTO, paymentSystem)) {
            return false;
        }

        Optional<Client> client = clientService.findByPassportNumber(cardIssuanceDTO.getPassportNumber());
        Client targetClient = client.orElseGet(() -> clientService.saveClient(cardIssuanceDTO));
        buildMasterCard(cardIssuanceDTO, targetClient, paymentSystem);

        return true;
    }

    @Override
    public boolean replenishBalance(CardReplenishmentDTO cardReplenishmentDTO) {
        return true;
    }

    @Override
    public boolean withdrawFunds(CardWithdrawalDTO cardWithdrawalDTO) {
        return true;
    }

    private void buildMasterCard(CardIssuanceDTO cardIssuanceDTO, Client client, PaymentSystem paymentSystem) {
        Card card = Card.builder()
                .client(client)
                .phoneNumber(cardIssuanceDTO.getPhoneNumber())
                .cardNumber(cardUtil.generateUniqueCardNumber("22212720", paymentSystem))
                .expirationDate(LocalDate.now().plusYears(3))
                .balance(0)
                .cvv(cardUtil.generateCVV())
                .paymentSystem(paymentSystem)
                .build();
        cardRepository.save(card);
    }
}
