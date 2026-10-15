# Payment Service

Requirement implementation:

`order-created` -> Payment Service -> `payment-success` OR `payment-failed`.

Responsibilities:
- Consume ORDER_CREATED.
- Process/simulate payment.
- Store payment result in MySQL.
- Publish PAYMENT_SUCCESS or PAYMENT_FAILED.
- Use `payment-service-group`.
- Duplicate ORDER_CREATED is ignored by orderId.
- Retry/DLT error handler is included. Failed records can go to `payment-success.DLT`.

For the demo, the payment result is deterministic:
- amount >= 50000 => SUCCESS
- amount < 50000 => FAILED

This makes both required flows easy to demonstrate without a real payment gateway.

Create:
CREATE DATABASE payment_db;

Run:
mvn clean install
mvn spring-boot:run
