package com.foodie.repository;

import java.util.ArrayList;
import java.util.List;

import com.foodie.model.Order;

public class OrderRepository {

    private List<Order> orders = new ArrayList<>();

    public void save(Order order) {
        orders.add(order);
    }

    public List<Order> findByCustomerId(int customerId) {

        List<Order> customerOrders = new ArrayList<>();

        for (Order order : orders) {

            if (order.getCustomerId() == customerId) {
                customerOrders.add(order);
            }
        }

        return customerOrders;
    }

    public int count() {
        return orders.size();
    }
}