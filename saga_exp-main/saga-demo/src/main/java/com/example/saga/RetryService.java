package com.example.saga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Experiment 9 - Retry (transient failure handling)
@Service
public class RetryService {

    @Autowired
    private InventoryService inventoryService;

    public void updateInventoryWithRetry(Long productId, int quantity) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                inventoryService.reserveStock(productId, quantity);
                System.out.println("Inventory updated");
                return;
            } catch (Exception e) {
                attempts++;
                System.out.println("Retry attempt: " + attempts);
            }
        }
        System.out.println("Inventory update failed after retries");
    }
}
