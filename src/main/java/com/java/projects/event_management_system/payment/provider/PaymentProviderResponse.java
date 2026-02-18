package com.java.projects.event_management_system.payment.provider;

public record PaymentProviderResponse(
        String reference,
        String paymentUrl
) {}
