package com.example.saga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Experiment 7 - CQRS write side
@Service
public class OrderCommandService {

    @Autowired
    private OrderRepository repository;

    public Order create(Order order) {
        order.setStatus("CREATED");
        return repository.save(order);
    }
}
