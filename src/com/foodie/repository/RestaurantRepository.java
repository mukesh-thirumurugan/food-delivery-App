package com.foodie.repository;

import java.util.ArrayList;
import java.util.List;

import com.foodie.model.MenuItem;
import com.foodie.model.Restaurant;

public class RestaurantRepository {

    private List<Restaurant> restaurants = new ArrayList<>();

    public RestaurantRepository() {
        loadSampleData();
    }

    private void loadSampleData() {

        Restaurant restaurant1 =
                new Restaurant(1, "Food Paradise");

        restaurant1.addMenuItem(new MenuItem(1, "Burger", 120));
        restaurant1.addMenuItem(new MenuItem(2, "Pizza", 250));
        restaurant1.addMenuItem(new MenuItem(3, "Sandwich", 100));

        Restaurant restaurant2 =
                new Restaurant(2, "South Indian Kitchen");

        restaurant2.addMenuItem(new MenuItem(4, "Dosa", 80));
        restaurant2.addMenuItem(new MenuItem(5, "Idli", 50));
        restaurant2.addMenuItem(new MenuItem(6, "Meals", 150));

        Restaurant restaurant3 =
                new Restaurant(3, "Spicy Biryani House");

        restaurant3.addMenuItem(new MenuItem(7, "Chicken Biryani", 220));
        restaurant3.addMenuItem(new MenuItem(8, "Veg Biryani", 160));
        restaurant3.addMenuItem(new MenuItem(9, "Chicken 65", 180));

        restaurants.add(restaurant1);
        restaurants.add(restaurant2);
        restaurants.add(restaurant3);
    }

    public List<Restaurant> findAll() {
        return new ArrayList<>(restaurants);
    }

    public Restaurant findById(int restaurantId) {

        for (Restaurant restaurant : restaurants) {

            if (restaurant.getRestaurantId() == restaurantId) {
                return restaurant;
            }
        }

        return null;
    }
}