# saga-demo

Order &ndash; Payment &ndash; Inventory Saga.
Spring Boot 3.3.4 &middot; Java 17 &middot; Spring Web, Spring Data JPA, H2 &middot; Maven &middot; embedded Tomcat on port 8080.

Chapter 4 &mdash; Structural Patterns and Chaining Processes, Experiments 1 to 10.

## Workflow

```
Create Order -> Make Payment -> Reserve Inventory -> Confirm Order

If Reserve Inventory fails after Payment has already succeeded:
Refund Payment -> Cancel Order
```

## Experiments 1 to 5 (Order/Payment/Inventory saga)

| Exp. | Concept | Class |
|---|---|---|
| 1 | Local ACID transaction | `OrderService` |
| 2 | Business invariant | `InventoryService` |
| 3 | Payment and compensation | `PaymentService` |
| 4 | Saga orchestration | `SagaService` |
| 5 | REST controller | `OrderController` |

Run and test:

```
mvn spring-boot:run
```

Open `http://localhost:8080`, or POST directly:

```
POST /orders
{ "productId": 101, "quantity": 1, "amount": 1500 }
```

## Experiments 6 to 10 (structural and resilience patterns)

All exposed through `Chapter4Controller`.

| Exp. | Concept | Class(es) | Endpoint |
|---|---|---|---|
| 6 | CQS | `OrderCqsService` | `POST /chapter4/cqs/orders`, `GET /chapter4/cqs/orders/{id}` |
| 7 | CQRS | `OrderCommandService`, `OrderQueryService` | `POST /chapter4/cqrs/orders`, `GET /chapter4/cqrs/orders/{id}`, `GET /chapter4/cqrs/orders` |
| 8 | Idempotency | `PaymentEventConsumer` | `POST /chapter4/idempotency?eventId=...&orderId=...` |
| 9 | Retry | `RetryService` | `POST /chapter4/retry?productId=...&quantity=...` |
| 10 | Reconciliation | `ReconciliationService` | `POST /chapter4/reconcile` |

## Supporting classes

`Order`, `Product` (entities), `OrderRepository`, `ProductRepository` (Spring Data JPA), `DataLoader` (seeds two products with ten units each at startup), `ViewController` (read-only endpoints used by the web page).

## Team

- Vijayaram V (2303917710421177)
- Thamarai Selvi A (2303917710422170)
- Thanya Shri S (2303917710422172)
