package com.ecommerce.order.dto;

import java.math.BigDecimal;

public class CreateOrderRequest {
    private Long customerId;
    private String customerName;
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal amount;
    private String deliveryAddress;

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long v) { customerId = v; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String v) { customerName = v; }
    public Long getProductId() { return productId; }
    public void setProductId(Long v) { productId = v; }
    public String getProductName() { return productName; }
    public void setProductName(String v) { productName = v; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer v) { quantity = v; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal v) { amount = v; }
    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String v) { deliveryAddress = v; }
}
