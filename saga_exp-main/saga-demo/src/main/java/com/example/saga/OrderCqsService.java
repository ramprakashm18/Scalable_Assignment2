package com.example.saga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Experiment 6 - CQS (Command Query Separation)
@Service
public class OrderCqsService {

    @Autowired
    private OrderRepository repository;

    // Command - changes state
    public Order createOrder(Order order) {
        order.setStatus("CREATED");
        return repository.save(order);
    }

    // Query - reads state
    public Order getOrder(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
