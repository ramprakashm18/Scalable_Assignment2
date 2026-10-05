package com.example.saga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Supporting class (not an experiment on its own) - read-only endpoints used by the web page
@RestController
public class ViewController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/api/orders")
    public List<Order> allOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/api/products")
    public List<Product> allProducts() {
        return productRepository.findAll();
    }
}
