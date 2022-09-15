package com.firstproject.orders.entities.enums;

import com.firstproject.orders.entities.Order;

import java.util.Arrays;

public enum OrderStatus {

    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPERED(3),
    DELIVERED(4),
    CANCELED(5);

    private int statusCode;

    OrderStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public static OrderStatus valueOf(int statusCode) {
        for (OrderStatus statusValue : OrderStatus.values()) {
            if (statusValue.getStatusCode() == statusCode) {
                return statusValue;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus Code");
    }

}
