package com.example.saga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Wires Experiments 6-10 together behind REST endpoints
@RestController
@RequestMapping("/chapter4")
public class Chapter4Controller {

    @Autowired
    private OrderCqsService cqsService;

    @Autowired
    private OrderCommandService commandService;
    @Autowired
    private OrderQueryService queryService;

    @Autowired
    private PaymentEventConsumer eventConsumer;

    @Autowired
    private RetryService retryService;

    @Autowired
    private ReconciliationService reconciliationService;

    // Experiment 6 - CQS
    @PostMapping("/cqs/orders")
    public Order cqsCreate(@RequestBody Order order) {
        return cqsService.createOrder(order);
    }

    @GetMapping("/cqs/orders/{id}")
    public Order cqsGet(@PathVariable Long id) {
        return cqsService.getOrder(id);
    }

    // Experiment 7 - CQRS
    @PostMapping("/cqrs/orders")
    public Order cqrsCreate(@RequestBody Order order) {
        return commandService.create(order);
    }

    @GetMapping("/cqrs/orders/{id}")
    public Order cqrsGetById(@PathVariable Long id) {
        return queryService.findById(id);
    }

    @GetMapping("/cqrs/orders")
    public List<Order> cqrsGetAll() {
        return queryService.findAll();
    }

    // Experiment 8 - Idempotency
    @PostMapping("/idempotency")
    public String idempotency(@RequestParam String eventId, @RequestParam Long orderId) {
        eventConsumer.handle(eventId, orderId);
        return "Event handled. Check console for duplicate-processing behavior.";
    }

    // Experiment 9 - Retry
    @PostMapping("/retry")
    public String retry(@RequestParam Long productId, @RequestParam int quantity) {
        retryService.updateInventoryWithRetry(productId, quantity);
        return "Retry process completed. Check console for retry attempts.";
    }

    // Experiment 10 - Reconciliation
    @PostMapping("/reconcile")
    public String reconcile(@RequestBody Order order) {
        reconciliationService.reconcile(order);
        return "Reconciliation check completed. See console output.";
    }
}
