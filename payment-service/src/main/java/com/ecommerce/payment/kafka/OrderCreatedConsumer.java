package com.ecommerce.payment.kafka;

import com.ecommerce.payment.entity.Payment;
import com.ecommerce.payment.event.OrderCreatedEvent;
import com.ecommerce.payment.event.PaymentFailedEvent;
import com.ecommerce.payment.event.PaymentSuccessEvent;
import com.ecommerce.payment.repository.PaymentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderCreatedConsumer {
    private final ObjectMapper objectMapper;
    private final PaymentRepository repository;
    private final PaymentKafkaProducer producer;

    public OrderCreatedConsumer(
            ObjectMapper objectMapper,
            PaymentRepository repository,
            PaymentKafkaProducer producer) {
        this.objectMapper = objectMapper;
        this.repository = repository;
        this.producer = producer;
    }

    @KafkaListener(
            topics = "order-created",
            groupId = "payment-service-group"
    )
    public void consume(String message) throws Exception {
        OrderCreatedEvent event =
                objectMapper.readValue(message, OrderCreatedEvent.class);

        if (repository.existsByOrderId(event.getOrderId())) {
            System.out.println("Duplicate ORDER_CREATED ignored for order "
                    + event.getOrderId());
            return;
        }

        /*
         * Deterministic demo rule:
         * amount >= 50000 -> SUCCESS
         * amount < 50000 -> FAILED
         *
         * This is only to make the required success/failure flow demonstrable.
         */
        boolean success = event.getAmount() != null
                && event.getAmount().doubleValue() >= 50000;

        Payment payment = new Payment();
        payment.setPaymentId("TXN-" + UUID.randomUUID().toString()
                .substring(0, 8).toUpperCase());
        payment.setOrderId(event.getOrderId());
        payment.setCustomerId(event.getCustomerId());
        payment.setAmount(event.getAmount());
        payment.setPaymentMethod("UPI");

        if (success) {
            payment.setPaymentStatus("SUCCESS");
            repository.save(payment);

            PaymentSuccessEvent successEvent = new PaymentSuccessEvent();
            successEvent.setEventId("PAY-" + event.getOrderId());
            successEvent.setEventType("PAYMENT_SUCCESS");
            successEvent.setOrderId(event.getOrderId());
            successEvent.setCustomerId(event.getCustomerId());
            successEvent.setAmount(event.getAmount());
            successEvent.setPaymentId(payment.getPaymentId());
            successEvent.setPaymentMethod(payment.getPaymentMethod());
            successEvent.setPaymentStatus("SUCCESS");
            successEvent.setDeliveryAddress(event.getDeliveryAddress());

            producer.publishSuccess(successEvent);

        } else {
            payment.setPaymentStatus("FAILED");
            payment.setReason("INSUFFICIENT_FUNDS");
            repository.save(payment);

            PaymentFailedEvent failedEvent = new PaymentFailedEvent();
            failedEvent.setEventId("PAY-" + event.getOrderId());
            failedEvent.setEventType("PAYMENT_FAILED");
            failedEvent.setOrderId(event.getOrderId());
            failedEvent.setCustomerId(event.getCustomerId());
            failedEvent.setAmount(event.getAmount());
            failedEvent.setPaymentId(payment.getPaymentId());
            failedEvent.setPaymentStatus("FAILED");
            failedEvent.setReason("INSUFFICIENT_FUNDS");

            producer.publishFailed(failedEvent);
        }
    }
}
