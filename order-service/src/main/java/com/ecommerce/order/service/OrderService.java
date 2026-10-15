package com.ecommerce.order.service;

import com.ecommerce.order.dto.CreateOrderRequest;
import com.ecommerce.order.entity.Order;
import com.ecommerce.order.event.OrderCreatedEvent;
import com.ecommerce.order.kafka.OrderKafkaProducer;
import com.ecommerce.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository repository;
    private final OrderKafkaProducer producer;

    public OrderService(OrderRepository repository, OrderKafkaProducer producer) {
        this.repository = repository;
        this.producer = producer;
    }

    @Transactional
    public Order create(CreateOrderRequest request) {
        Order order = new Order();
        order.setCustomerId(request.getCustomerId());
        order.setCustomerName(request.getCustomerName());
        order.setProductId(request.getProductId());
        order.setProductName(request.getProductName());
        order.setQuantity(request.getQuantity());
        order.setAmount(request.getAmount());
        order.setDeliveryAddress(request.getDeliveryAddress());
        order.setStatus("CREATED");

        Order saved = repository.save(order);

        OrderCreatedEvent event = new OrderCreatedEvent();
        event.setEventId("EVT-" + saved.getOrderId());
        event.setEventType("ORDER_CREATED");
        event.setOrderId(saved.getOrderId());
        event.setCustomerId(saved.getCustomerId());
        event.setAmount(saved.getAmount());
        event.setDeliveryAddress(saved.getDeliveryAddress());
        event.setEventTime(LocalDateTime.now());

        producer.publish(event);
        return saved;
    }

    public List<Order> findAll() { return repository.findAll(); }

    public Order findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));
    }
}
