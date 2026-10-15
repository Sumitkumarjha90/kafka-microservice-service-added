# Kafka Microservices - Remaining 3 Services

Generated according to the attached Order Processing System requirements.

Included:
1. order-service
2. payment-service
3. notification-service

The Delivery Service is separate and was generated previously.

Business flow:

CUSTOMER
  |
  | POST /orders
  v
ORDER SERVICE
  |
  | ORDER_CREATED
  v
Kafka: order-created
  |
  v
PAYMENT SERVICE
  |
  +--> PAYMENT_SUCCESS --> Kafka: payment-success --> DELIVERY SERVICE
  |
  +--> PAYMENT_FAILED  --> Kafka: payment-failed  --> NOTIFICATION SERVICE

DELIVERY SERVICE
  |
  | DELIVERY_CREATED
  v
Kafka: delivery-created
  |
  v
NOTIFICATION SERVICE
  |
  v
SMS/Email simulation

Ports:
- Order Service: 8081
- Payment Service: 8082
- Notification Service: 8084

MySQL databases:
- order_db
- payment_db
- delivery_db (used by the separately generated Delivery Service)

Kafka topics:
- order-created
- payment-success
- payment-failed
- delivery-created
- payment-success.DLT

Consumer groups:
- payment-service-group
- delivery-service-group
- notification-service-group

The implementation uses orderId as the Kafka message key, multiple partitions,
idempotency checks, and a retry/DLT configuration in Payment Service.

The payment service uses a deterministic demo rule:
amount >= 50000 -> SUCCESS
amount < 50000 -> FAILED

This is only to simulate the payment outcome without an external payment gateway.
