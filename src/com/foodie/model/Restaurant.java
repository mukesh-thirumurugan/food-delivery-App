package com.foodie.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private int restaurantId;
    private String name;
    private List<MenuItem> menuItems;

    public Restaurant(int restaurantId, String name) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.menuItems = new ArrayList<>();
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public List<MenuItem> getMenuItems() {
        return new ArrayList<>(menuItems);
    }

    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    @Override
    public String toString() {
        return restaurantId + ". " + name;
    }
}