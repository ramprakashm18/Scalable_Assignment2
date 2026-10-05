package com.example.saga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Experiment 2 - Business invariant (stock must never go negative)
@Service
public class InventoryService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public boolean reserveStock(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow();

        if (product.getStock() < quantity) {
            return false;
        }

        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
        return true;
    }

    @Transactional
    public void restoreStock(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow();
        product.setStock(product.getStock() + quantity);
        productRepository.save(product);
    }
}
