package com.foodie.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private int orderId;
    private int customerId;
    private String restaurantName;

    private List<OrderItem> items;

    private double total;
    private LocalDateTime orderTime;

    public Order(
            int orderId,
            int customerId,
            String restaurantName,
            List<OrderItem> items,
            double total) {

        this.orderId = orderId;
        this.customerId = customerId;
        this.restaurantName = restaurantName;
        this.items = new ArrayList<>(items);
        this.total = total;
        this.orderTime = LocalDateTime.now();
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public List<OrderItem> getItems() {
        return new ArrayList<>(items);
    }

    public double getTotal() {
        return total;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();

        result.append("\nOrder ID: ").append(orderId);
        result.append("\nRestaurant: ").append(restaurantName);
        result.append("\nOrder Time: ").append(orderTime);

        result.append("\nItems:\n");

        for (OrderItem item : items) {
            result.append(item).append("\n");
        }

        result.append("Total: Rs. ").append(total);

        return result.toString();
    }
}