package com.java.projects.event_management_system.payment.entity;

import com.java.projects.event_management_system.common.PaymentStatus;
import com.java.projects.event_management_system.booking.entity.Booking;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "payments",
       uniqueConstraints = {
        @UniqueConstraint(columnNames = "provider_reference"),
               @UniqueConstraint(columnNames = "booking_id")
       })
public class Payment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    @Column(nullable = false)
    private Long version;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false, unique = true)
    private Booking booking;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name = "provider_reference", nullable = false)
    private String providerReference;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    protected Payment(){}

    public Payment(Booking booking, BigDecimal amount, String providerReference){
        this.booking = booking;
        this.amount = amount;
        this.providerReference = providerReference;
        this.status = PaymentStatus.INITIATED;
    }

    public void markSuccess() {
        if (this.status == PaymentStatus.SUCCESS) return;
        this.status = PaymentStatus.SUCCESS;
    }

    public void markFailed() {
        this.status = PaymentStatus.FAILED;
    }


    public Long getId() {
        return id;
    }

    public Booking getBooking() {
        return booking;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getProviderReference() {
        return providerReference;
    }

    public PaymentStatus getStatus() {
        return status;
    }
}