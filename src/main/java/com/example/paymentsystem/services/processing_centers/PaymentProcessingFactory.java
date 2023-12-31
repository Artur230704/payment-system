package com.example.paymentsystem.services.processing_centers;

import com.example.paymentsystem.exceptions.PaymentSystemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PaymentProcessingFactory {
    private final Map<String, PaymentProcessingStrategy> strategies;

    // конструктор собирает все классы, которые реализовали стратегию
    @Autowired
    public PaymentProcessingFactory(List<PaymentProcessingStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(
                        strategy -> getPaymentProcessingCenterName(strategy.getClass()),
                        Function.identity()
                ));
    }

    // получаем имя процессингово центра(имя возле аннотации @Service)
    private String getPaymentProcessingCenterName(Class<? extends PaymentProcessingStrategy> strategyClass) {
        Service serviceAnnotation = strategyClass.getAnnotation(Service.class);
        return (serviceAnnotation != null) ? serviceAnnotation.value() : "";
    }

    // Возвращаем нужный процессинговый центр по имени платежной систмы
    public PaymentProcessingStrategy getPaymentStrategy(String paymentSystem) {
        for (Map.Entry<String, PaymentProcessingStrategy> entry : strategies.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(paymentSystem)) {
                return entry.getValue();
            }
        }
        throw new PaymentSystemNotFoundException("Платежная система не найдена");
    }
}
