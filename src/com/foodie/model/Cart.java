package com.foodie.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private int restaurantId;
    private String restaurantName;

    private List<CartItem> items = new ArrayList<>();

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public boolean addItem(Restaurant restaurant, MenuItem menuItem, int quantity) {

        if (quantity <= 0) {
            return false;
        }

        if (!items.isEmpty() && restaurantId != restaurant.getRestaurantId()) {
            return false;
        }

        if (items.isEmpty()) {
            restaurantId = restaurant.getRestaurantId();
            restaurantName = restaurant.getName();
        }

        for (CartItem item : items) {

            if (item.getMenuItem().getItemId() == menuItem.getItemId()) {
                item.increaseQuantity(quantity);
                return true;
            }
        }

        items.add(new CartItem(menuItem, quantity));

        return true;
    }

    public double getTotal() {

        double total = 0;

        for (CartItem item : items) {
            total += item.getSubtotal();
        }

        return total;
    }

    public void clear() {
        items.clear();
        restaurantId = 0;
        restaurantName = null;
    }
}