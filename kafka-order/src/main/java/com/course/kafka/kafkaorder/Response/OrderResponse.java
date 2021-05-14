package com.course.kafka.kafkaorder.Response;

public class OrderResponse {

    private String orderNumber;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public OrderResponse(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public OrderResponse() {
    }

    @Override
    public String toString() {
        return "OrderResponse [orderNumber=" + orderNumber + "]";
    }

}
