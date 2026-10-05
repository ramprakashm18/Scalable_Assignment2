package com.example.saga;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

// Experiment 8 - Idempotency (idempotent event handling)
@Service
public class PaymentEventConsumer {

    private final Set<String> processedEvents = new HashSet<>();

    public void handle(String eventId, Long orderId) {
        if (processedEvents.contains(eventId)) {
            System.out.println("Duplicate event ignored");
            return;
        }
        System.out.println("Processing payment for order " + orderId);
        processedEvents.add(eventId);
    }
}
