package com.example.saga;

import org.springframework.stereotype.Service;

// Experiment 10 - Reconciliation (recovering unfinished workflows)
@Service
public class ReconciliationService {

    public void reconcile(Order order) {
        if ("PAID".equals(order.getStatus())) {
            System.out.println(
                    "Order is paid but not confirmed. Check inventory workflow.");
        }
        if ("CANCELLED".equals(order.getStatus())) {
            System.out.println(
                    "Verify that compensation/refund completed.");
        }
    }
}
