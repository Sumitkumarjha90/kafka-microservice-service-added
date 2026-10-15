package com.ecommerce.notification.event;

public class NotificationEvent {
    private String eventId;
    private String eventType;
    private Long orderId;
    private Long customerId;
    private String paymentStatus;
    private String paymentId;
    private String reason;
    private String trackingNumber;
    private String deliveryAddress;
    private String deliveryStatus;

    public NotificationEvent() {}

    public String getEventId() { return eventId; }
    public void setEventId(String v) { eventId = v; }
    public String getEventType() { return eventType; }
    public void setEventType(String v) { eventType = v; }
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long v) { orderId = v; }
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long v) { customerId = v; }
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String v) { paymentStatus = v; }
    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String v) { paymentId = v; }
    public String getReason() { return reason; }
    public void setReason(String v) { reason = v; }
    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String v) { trackingNumber = v; }
    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String v) { deliveryAddress = v; }
    public String getDeliveryStatus() { return deliveryStatus; }
    public void setDeliveryStatus(String v) { deliveryStatus = v; }
}
