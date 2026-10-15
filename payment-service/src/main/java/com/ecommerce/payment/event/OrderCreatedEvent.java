package com.ecommerce.payment.event;

import java.math.BigDecimal;

public class OrderCreatedEvent {
    private String eventId;
    private String eventType;
    private Long orderId;
    private Long customerId;
    private BigDecimal amount;
    private String deliveryAddress;

    public OrderCreatedEvent() {}

    public String getEventId() { return eventId; }
    public void setEventId(String v) { eventId = v; }
    public String getEventType() { return eventType; }
    public void setEventType(String v) { eventType = v; }
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long v) { orderId = v; }
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long v) { customerId = v; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal v) { amount = v; }
    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String v) { deliveryAddress = v; }
}
