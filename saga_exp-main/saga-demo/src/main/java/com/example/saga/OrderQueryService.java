package com.example.saga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Experiment 7 - CQRS read side
@Service
public class OrderQueryService {

    @Autowired
    private OrderRepository repository;

    public Order findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Order> findAll() {
        return repository.findAll();
    }
}
