package com.ecommerce.payment.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic orderCreated() {
        return TopicBuilder.name("order-created").partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic paymentSuccess() {
        return TopicBuilder.name("payment-success").partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic paymentFailed() {
        return TopicBuilder.name("payment-failed").partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic paymentSuccessDlt() {
        return TopicBuilder.name("payment-success.DLT").partitions(3).replicas(1).build();
    }
}
