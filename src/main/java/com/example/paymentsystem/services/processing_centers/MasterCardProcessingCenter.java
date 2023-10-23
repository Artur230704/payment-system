package com.example.paymentsystem.services.processing_centers;

import com.example.paymentsystem.dtos.CardIssuanceDTO;
import com.example.paymentsystem.dtos.CardReplenishmentDTO;
import com.example.paymentsystem.dtos.CardWithdrawalDTO;
import com.example.paymentsystem.entities.Card;
import com.example.paymentsystem.entities.Client;
import com.example.paymentsystem.entities.PaymentSystem;
import com.example.paymentsystem.exceptions.*;
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
    public String issueCard(CardIssuanceDTO cardIssuanceDTO) {
        PaymentSystem paymentSystem = paymentSystemService.findBySystemName(cardIssuanceDTO.getPaymentSystem());

        if (cardUtil.isCardIssuedForPhoneNumber(cardIssuanceDTO, paymentSystem)) {
            throw new CardAlreadyIssuedException("На данный номер телефона уже зарегестрирована карта MasterCard");
        }

        Optional<Client> client = clientService.findByPassportNumber(cardIssuanceDTO.getPassportNumber());
        Client targetClient = client.orElseGet(() -> clientService.saveClient(cardIssuanceDTO));
        buildMasterCard(cardIssuanceDTO, targetClient, paymentSystem);

        return "Карта MasterCard выпущена";
    }


    @Override
    public String replenishBalance(CardReplenishmentDTO cardReplenishmentDTO) {
        PaymentSystem paymentSystem = paymentSystemService.findBySystemName(cardReplenishmentDTO.getPaymentSystem());
        Card card = cardRepository.findByCardNumberAndPaymentSystem(cardReplenishmentDTO.getCardNumber(), paymentSystem)
                .orElseThrow(() -> new CardNotFoundException("Карта MasterCard не найдена"));

        if (!cardUtil.isExpired(card.getExpirationDate())) {
            throw new CardIsExpiredException("Срок действия карты прошел");
        }

        double replenishmentAmount = cardReplenishmentDTO.getAmount();
        double percent = paymentSystem.getPercent();
        double amountToAdd = replenishmentAmount - (replenishmentAmount * percent / 100);

        card.setBalance(card.getBalance() + amountToAdd);
        cardRepository.save(card);

        return "Баланс пополнен";
    }

    @Override
    public String withdrawFunds(CardWithdrawalDTO cardWithdrawalDTO) {
        PaymentSystem paymentSystem = paymentSystemService.findBySystemName(cardWithdrawalDTO.getPaymentSystem());
        Card card = cardRepository.findByCardNumberAndPaymentSystem(cardWithdrawalDTO.getCardNumber(), paymentSystem)
                .orElseThrow(() -> new CardNotFoundException("Карта MasterCard не найдена"));

        if (!cardUtil.pinCodeIsCorrect(cardWithdrawalDTO, card)) {
            throw new InvalidPinException("Неверный пин-код");
        }

        if (!cardUtil.isEnoughFunds(cardWithdrawalDTO, card)) {
            throw new NotEnoughFundsException("Недостаточно средств на вашей карте MasterCard");
        }

        if (!cardUtil.isExpired(card.getExpirationDate())) {
            throw new CardIsExpiredException("Срок действия карты прошел");
        }

        card.setBalance(card.getBalance() - cardWithdrawalDTO.getAmount());
        cardRepository.save(card);

        return "Средства сняты!";
    }

    private void buildMasterCard(CardIssuanceDTO cardIssuanceDTO, Client client, PaymentSystem paymentSystem) {
        Card card = Card.builder()
                .client(client)
                .phoneNumber(cardIssuanceDTO.getPhoneNumber())
                .cardNumber(cardUtil.generateUniqueCardNumber("22212720", paymentSystem))
                .issueDate(LocalDate.now())
                .expirationDate(LocalDate.now().plusYears(3))
                .balance(0)
                .pinCode(cardUtil.generatePinCode())
                .paymentSystem(paymentSystem)
                .build();
        cardRepository.save(card);
    }
}
