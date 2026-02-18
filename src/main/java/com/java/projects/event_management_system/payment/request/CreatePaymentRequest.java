package com.java.projects.event_management_system.payment.request;

import jakarta.validation.constraints.NotBlank;

public class CreatePaymentRequest {
    @NotBlank
    private String providerReference;
}
