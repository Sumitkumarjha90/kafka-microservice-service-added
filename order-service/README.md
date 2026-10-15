# Order Service

Requirement flow:
POST /orders -> store order -> publish ORDER_CREATED to `order-created`.

Create database:
CREATE DATABASE order_db;

Run:
mvn clean install
mvn spring-boot:run

POST http://localhost:8081/orders

{
  "customerId": 101,
  "customerName": "Rahul",
  "productId": 501,
  "productName": "Laptop",
  "quantity": 1,
  "amount": 75000,
  "deliveryAddress": "Bangalore"
}

Response:
{
  "orderId": 1001,
  "status": "CREATED"
}
