package com.ecommerce.notification.kafka;

import com.ecommerce.notification.event.NotificationEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {
    private final ObjectMapper objectMapper;

    public NotificationConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(
            topics = {"order-created", "payment-success", "payment-failed", "delivery-created"},
            groupId = "notification-service-group"
    )
    public void consume(String message) {
        try {
            NotificationEvent event =
                    objectMapper.readValue(message, NotificationEvent.class);

            sendNotification(event);
        } catch (Exception e) {
            System.err.println("Notification processing failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void sendNotification(NotificationEvent event) {
        String type = event.getEventType();

        System.out.println();
        System.out.println("========================================");
        System.out.println("          SMS SENT / EMAIL SENT");
        System.out.println("Customer: " + event.getCustomerId());
        System.out.println("Order: " + event.getOrderId());

        if ("ORDER_CREATED".equalsIgnoreCase(type)) {
            System.out.println("Your order has been created.");
        } else if ("PAYMENT_SUCCESS".equalsIgnoreCase(type)) {
            System.out.println("Your payment was successful.");
        } else if ("PAYMENT_FAILED".equalsIgnoreCase(type)) {
            System.out.println("Your payment failed. Reason: " + event.getReason());
        } else if ("DELIVERY_CREATED".equalsIgnoreCase(type)) {
            System.out.println("Your delivery was created. Tracking: "
                    + event.getTrackingNumber());
        } else if ("DELIVERY_OUT_FOR_DELIVERY".equalsIgnoreCase(type)) {
            System.out.println("Your order is out for delivery.");
        } else if ("ORDER_DELIVERED".equalsIgnoreCase(type)) {
            System.out.println("Your order has been delivered.");
        } else {
            System.out.println("Notification for event: " + type);
        }

        System.out.println("========================================");
        System.out.println();
    }
}
