package com.foodie.service;

import java.util.ArrayList;
import java.util.List;

import com.foodie.model.Cart;
import com.foodie.model.CartItem;
import com.foodie.model.Customer;
import com.foodie.model.Order;
import com.foodie.model.OrderItem;
import com.foodie.repository.OrderRepository;

public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order placeOrder(Customer customer, Cart cart) {

        if (customer == null) {
            System.out.println("Please login first!");
            return null;
        }

        if (cart == null || cart.isEmpty()) {
            System.out.println("Your cart is empty!");
            return null;
        }

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {

            OrderItem orderItem = new OrderItem(
                    cartItem.getMenuItem().getName(),
                    cartItem.getMenuItem().getPrice(),
                    cartItem.getQuantity()
            );

            orderItems.add(orderItem);
        }

        int orderId = orderRepository.count() + 1;

        Order order = new Order(
                orderId,
                customer.getCustomerId(),
                cart.getRestaurantName(),
                orderItems,
                cart.getTotal()
        );

        orderRepository.save(order);

        cart.clear();

        System.out.println("Order placed successfully!");

        return order;
    }

    public List<Order> getCustomerOrders(int customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
}