package com.ecommerce.payment.event;

import java.math.BigDecimal;

public class PaymentFailedEvent {
    private String eventId;
    private String eventType;
    private Long orderId;
    private Long customerId;
    private BigDecimal amount;
    private String paymentId;
    private String paymentStatus;
    private String reason;

    public PaymentFailedEvent() {}

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
    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String v) { paymentId = v; }
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String v) { paymentStatus = v; }
    public String getReason() { return reason; }
    public void setReason(String v) { reason = v; }
}
