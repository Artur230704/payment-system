package com.example.paymentsystem;

import com.example.paymentsystem.dtos.CardIssuanceDTO;
import com.example.paymentsystem.dtos.CardReplenishmentDTO;
import com.example.paymentsystem.dtos.CardWithdrawalDTO;
import com.example.paymentsystem.entities.Card;
import com.example.paymentsystem.entities.PaymentSystem;
import com.example.paymentsystem.exceptions.CardAlreadyIssuedException;
import com.example.paymentsystem.exceptions.CardNotFoundException;
import com.example.paymentsystem.exceptions.NotEnoughFundsException;
import com.example.paymentsystem.repostiories.CardRepository;
import com.example.paymentsystem.services.ClientService;
import com.example.paymentsystem.services.PaymentSystemService;
import com.example.paymentsystem.services.processing_centers.MasterCardProcessingCenter;
import com.example.paymentsystem.utils.CardUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MasterCardProcessingCenterTest {
    private MasterCardProcessingCenter masterCardProcessingCenter;
    private PaymentSystemService paymentSystemService;
    private ClientService clientService;
    private CardRepository cardRepository;
    private CardUtil cardUtil;
    @BeforeEach
    public void setUp() {
        paymentSystemService = mock(PaymentSystemService.class);
        clientService = mock(ClientService.class);
        cardRepository = mock(CardRepository.class);
        cardUtil = mock(CardUtil.class);

        masterCardProcessingCenter = new MasterCardProcessingCenter(paymentSystemService, clientService, cardRepository, cardUtil);
    }

    @Test
    public void testIssueCard_successful() {
        CardIssuanceDTO cardIssuanceDTO = CardIssuanceDTO.builder()
                .passportNumber("1111111")
                .paymentSystem("MasterCard")
                .firstName("Artur")
                .lastName("Aidarov")
                .phoneNumber("+996770230704")
                .build();
        PaymentSystem paymentSystem = new PaymentSystem();

        when(paymentSystemService.findBySystemName(anyString())).thenReturn(paymentSystem);
        when(cardUtil.isCardIssuedForPhoneNumber(any(CardIssuanceDTO.class), any(PaymentSystem.class))).thenReturn(false);
        when(clientService.findByPassportNumber(anyString())).thenReturn(Optional.empty());

        String result = masterCardProcessingCenter.issueCard(cardIssuanceDTO);

        assertEquals("Карта MasterCard выпущена", result);
    }

    @Test
    public void testIssueCard_unsuccessful() {
        CardIssuanceDTO cardIssuanceDTO = CardIssuanceDTO.builder()
                .passportNumber("1111111")
                .paymentSystem("MasterCard")
                .firstName("Artur")
                .lastName("Aidarov")
                .phoneNumber("+996770230704")
                .build();
        PaymentSystem paymentSystem = new PaymentSystem();

        when(paymentSystemService.findBySystemName(anyString())).thenReturn(paymentSystem);
        when(cardUtil.isCardIssuedForPhoneNumber(any(CardIssuanceDTO.class), any(PaymentSystem.class))).thenReturn(true);
        when(clientService.findByPassportNumber(anyString())).thenReturn(Optional.empty());

        assertThrows(CardAlreadyIssuedException.class, () -> masterCardProcessingCenter.issueCard(cardIssuanceDTO));
    }

    @Test
    public void testReplenishBalance_successful() {
        CardReplenishmentDTO cardReplenishmentDTO = CardReplenishmentDTO.builder()
                .paymentSystem("MasterCard")
                .cardNumber("4169585390106621")
                .amount(100)
                .build();

        PaymentSystem paymentSystem = new PaymentSystem();
        Card card = Card.builder()
                .paymentSystem(paymentSystem)
                .cardNumber("4169585390106621")
                .balance(0)
                .build();

        when(paymentSystemService.findBySystemName(anyString())).thenReturn(paymentSystem);
        when(cardRepository.findByCardNumberAndPaymentSystem(cardReplenishmentDTO.getCardNumber(), paymentSystem)).thenReturn(Optional.of(card));
        String result = masterCardProcessingCenter.replenishBalance(cardReplenishmentDTO);

        assertEquals("Баланс пополнен", result);
        assertEquals(100, card.getBalance()); // тут не учитывается коммисия
    }

    @Test
    public void testReplenishBalance_unsuccessful() {
        CardReplenishmentDTO cardReplenishmentDTO = CardReplenishmentDTO.builder()
                .cardNumber("4169585390106621")
                .amount(100)
                .build();

        PaymentSystem paymentSystem = new PaymentSystem();
        Card card = Card.builder()
                .paymentSystem(paymentSystem)
                .cardNumber("4169585390106621")
                .balance(0)
                .build();

        when(paymentSystemService.findBySystemName(anyString())).thenReturn(paymentSystem);
        when(cardRepository.findByCardNumberAndPaymentSystem(cardReplenishmentDTO.getCardNumber(), paymentSystem)).thenReturn(Optional.of(card));

        assertThrows(CardNotFoundException.class, () -> masterCardProcessingCenter.replenishBalance(cardReplenishmentDTO));
    }

    @Test
    public void testWithdrawFunds_successful() {
        CardWithdrawalDTO cardWithdrawalDTO = CardWithdrawalDTO.builder()
                .paymentSystem("MasterCard")
                .cardNumber("4169585390106621")
                .amount(50)
                .pinCode("1234")
                .build();

        PaymentSystem paymentSystem = new PaymentSystem();
        Card card = Card.builder()
                .paymentSystem(paymentSystem)
                .cardNumber("4169585390106621")
                .pinCode("1234")
                .balance(100)
                .build();

        when(paymentSystemService.findBySystemName(anyString())).thenReturn(paymentSystem);
        when(cardRepository.findByCardNumberAndPaymentSystem(cardWithdrawalDTO.getCardNumber(), paymentSystem)).thenReturn(Optional.of(card));
        when(cardUtil.pinCodeIsCorrect(any(CardWithdrawalDTO.class), any(Card.class))).thenReturn(true);
        when(cardUtil.isEnoughFunds(any(CardWithdrawalDTO.class), any(Card.class))).thenReturn(true);

        String result = masterCardProcessingCenter.withdrawFunds(cardWithdrawalDTO);

        assertEquals("Средства сняты!", result);
        assertEquals(50, card.getBalance());
    }

    @Test
    public void testWithdrawFunds_unsuccessful() {
        CardWithdrawalDTO cardWithdrawalDTO = CardWithdrawalDTO.builder()
                .paymentSystem("MasterCard")
                .cardNumber("4169585390106621")
                .amount(200)
                .pinCode("1234")
                .build();

        PaymentSystem paymentSystem = new PaymentSystem();
        Card card = Card.builder()
                .paymentSystem(paymentSystem)
                .cardNumber("4169585390106621")
                .pinCode("1234")
                .balance(100)
                .build();

        when(paymentSystemService.findBySystemName(anyString())).thenReturn(paymentSystem);
        when(cardRepository.findByCardNumberAndPaymentSystem(cardWithdrawalDTO.getCardNumber(), paymentSystem)).thenReturn(Optional.of(card));
        when(cardUtil.pinCodeIsCorrect(any(CardWithdrawalDTO.class), any(Card.class))).thenReturn(true);

        assertThrows(NotEnoughFundsException.class, () -> masterCardProcessingCenter.withdrawFunds(cardWithdrawalDTO));
    }
}
