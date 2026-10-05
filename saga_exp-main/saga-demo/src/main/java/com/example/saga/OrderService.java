package com.example.saga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Experiment 1 - Local ACID transaction
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Order saveOrder(Order order) {
        order.setStatus("CREATED");
        return orderRepository.save(order);
    }
}
