package com.example.saga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) {
        productRepository.save(new Product(101L, "Product 101", 10));
        productRepository.save(new Product(102L, "Product 102", 10));
    }
}
