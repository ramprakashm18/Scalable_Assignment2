package com.example.saga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Experiment 5 - REST controller
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private SagaService sagaService;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return sagaService.placeOrder(order);
    }
}
