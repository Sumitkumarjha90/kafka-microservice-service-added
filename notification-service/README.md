# Notification Service

Requirement implementation:

Consumes:
- ORDER_CREATED
- PAYMENT_SUCCESS
- PAYMENT_FAILED
- DELIVERY_CREATED

Consumer group:
`notification-service-group`

No real SMS/email provider is required. The service prints simulated SMS/email
notifications to the console, exactly as required by the project.

Run:
mvn clean install
mvn spring-boot:run

Port:
8084
