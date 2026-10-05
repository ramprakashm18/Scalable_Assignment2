package com.example.saga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Experiment 4 - Saga orchestration (deliberately no @Transactional here)
@Service
public class SagaService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private PaymentService paymentService;

    public Order placeOrder(Order order) {
        // Step 1 - Create order
        order.setStatus("CREATED");
        orderRepository.save(order);

        // Step 2 - Payment
        boolean paid = paymentService.makePayment(order.getAmount());
        if (!paid) {
            order.setStatus("PAYMENT_FAILED");
            return orderRepository.save(order);
        }

        order.setStatus("PAID");
        orderRepository.save(order);

        // Step 3 - Reserve inventory
        boolean reserved = inventoryService.reserveStock(
                order.getProductId(), order.getQuantity());

        if (!reserved) {
            // Compensation
            paymentService.refund(order.getAmount());
            order.setStatus("CANCELLED");
            return orderRepository.save(order);
        }

        // Step 4 - Confirm order
        order.setStatus("CONFIRMED");
        return orderRepository.save(order);
    }
}
