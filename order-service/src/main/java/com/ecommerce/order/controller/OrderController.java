package com.ecommerce.order.controller;

import com.ecommerce.order.dto.CreateOrderRequest;
import com.ecommerce.order.entity.Order;
import com.ecommerce.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> create(@RequestBody CreateOrderRequest request) {
        Order order = service.create(request);
        return Map.of("orderId", order.getOrderId(), "status", order.getStatus());
    }

    @GetMapping
    public List<Order> all() { return service.findAll(); }

    @GetMapping("/{id}")
    public Order one(@PathVariable Long id) { return service.findById(id); }
}
