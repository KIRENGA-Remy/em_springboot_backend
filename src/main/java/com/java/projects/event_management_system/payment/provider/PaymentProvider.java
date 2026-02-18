package com.java.projects.event_management_system.payment.provider;

import java.math.BigDecimal;

public interface PaymentProvider {
    PaymentProviderResponse initiatePayment(BigDecimal amount);
}
