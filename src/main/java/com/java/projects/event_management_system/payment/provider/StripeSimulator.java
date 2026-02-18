package com.java.projects.event_management_system.payment.provider;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class StripeSimulator implements PaymentProvider{

    @Override
    public PaymentProviderResponse initiatePayment(BigDecimal amount) {
        String reference = UUID.randomUUID().toString();

        return new PaymentProviderResponse(
            reference,
            "https://fake-stripe.com/pay/" + reference
        );
    }
}