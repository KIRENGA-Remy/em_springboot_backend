package com.java.projects.event_management_system.payment.controller;


import com.java.projects.event_management_system.payment.request.PaymentWebhookRequest;
import com.java.projects.event_management_system.payment.service.PaymentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments/webhook")
public class PaymentWebhookController {

    private final PaymentService paymentService;

    public PaymentWebhookController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public void handleWebhook(@RequestBody PaymentWebhookRequest request){
        paymentService.handlePaymentResult(
                request.getReference(),
                request.getStatus()
        );
    }
}