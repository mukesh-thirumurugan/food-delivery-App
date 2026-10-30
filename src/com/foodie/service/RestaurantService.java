package com.foodie.service;

import java.util.List;

import com.foodie.model.Restaurant;
import com.foodie.repository.RestaurantRepository;

public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(int restaurantId) {
        return restaurantRepository.findById(restaurantId);
    }
}