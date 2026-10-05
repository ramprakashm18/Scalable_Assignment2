package com.example.saga;

import org.springframework.stereotype.Service;

// Experiment 3 - Payment and compensation (no @Transactional: external system)
@Service
public class PaymentService {

    public boolean makePayment(double amount) {
        System.out.println("Payment successful: Rs." + amount);
        return true;
    }

    public void refund(double amount) {
        System.out.println("Payment refunded: Rs." + amount);
    }
}
