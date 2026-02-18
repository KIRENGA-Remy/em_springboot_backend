package com.java.projects.event_management_system.payment.service;

import com.java.projects.event_management_system.common.BookingStatus;
import com.java.projects.event_management_system.common.PaymentStatus;
import com.java.projects.event_management_system.booking.entity.Booking;
import com.java.projects.event_management_system.payment.entity.Payment;
import com.java.projects.event_management_system.payment.provider.PaymentProvider;
import com.java.projects.event_management_system.booking.repository.BookingRepository;
import com.java.projects.event_management_system.payment.provider.PaymentProviderResponse;
import com.java.projects.event_management_system.payment.repository.PaymentRepository;
import com.java.projects.event_management_system.security.util.SecurityUtil;
import org.springframework.security.access.AccessDeniedException;
import com.java.projects.event_management_system.ticket.service.TicketService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final PaymentProvider paymentProvider;
    private final TicketService ticketService;

    public PaymentService(
            PaymentRepository paymentRepository,
            BookingRepository bookingRepository,
            PaymentProvider paymentProvider,
            TicketService ticketService){
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
        this.paymentProvider = paymentProvider;
        this.ticketService = ticketService;
    }

    @Transactional
    public Payment initiatePayment(Long bookingId){
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (booking.getStatus() != BookingStatus.PENDING){
            throw new IllegalStateException("Booking not payable");
        }

        Long currentUserId = SecurityUtil.getCurrentUserId();
        if (!booking.getUser().getId().equals(currentUserId))
            throw new AccessDeniedException("Not your booking");

        BigDecimal amount = calculateAmount(booking);

        PaymentProviderResponse providerResponse = paymentProvider.initiatePayment(amount);

        Payment payment = new Payment(
                booking,
                amount,
                providerResponse.reference()
        );

        return paymentRepository.save(payment);
    }

    public BigDecimal calculateAmount(Booking booking){
        return BigDecimal.valueOf(50);
    }

    @Transactional
    public void handlePaymentResult(String reference, PaymentStatus status){
        Payment payment = paymentRepository
                .findByProviderReference(reference)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        if(payment.getStatus() == PaymentStatus.SUCCESS){
            return;
        }

        if(status == PaymentStatus.SUCCESS){
            payment.markSuccess();
            payment.getBooking().confirm();
            ticketService.generateTicket(payment.getBooking());
        } else {
            payment.markFailed();
            payment.getBooking().cancel();
        }
    }
}