package com.ecommerce.payment.kafka;

import com.ecommerce.payment.event.PaymentFailedEvent;
import com.ecommerce.payment.event.PaymentSuccessEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentKafkaProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PaymentKafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishSuccess(PaymentSuccessEvent event) {
        kafkaTemplate.send("payment-success", event.getOrderId().toString(), event);
    }

    public void publishFailed(PaymentFailedEvent event) {
        kafkaTemplate.send("payment-failed", event.getOrderId().toString(), event);
    }
}
