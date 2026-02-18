package com.java.projects.event_management_system.payment.request;

import com.java.projects.event_management_system.common.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PaymentWebhookRequest {
    @NotBlank
    private String reference;
    @NotNull
    private PaymentStatus status;

    public String getReference() {
        return reference;
    }

    public PaymentStatus getStatus() {
        return status;
    }
}
