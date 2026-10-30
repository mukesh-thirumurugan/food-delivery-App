package com.foodie.service;

import java.util.List;

import com.foodie.model.Cart;
import com.foodie.model.CartItem;
import com.foodie.model.MenuItem;
import com.foodie.model.Restaurant;

public class CartService {

    private Cart cart = new Cart();

    public boolean addItem(
            Restaurant restaurant,
            MenuItem menuItem,
            int quantity) {

        if (restaurant == null || menuItem == null) {
            System.out.println("Invalid restaurant or food item!");
            return false;
        }

        boolean added = cart.addItem(restaurant, menuItem, quantity);

        if (!added) {
            System.out.println(
                    "Invalid quantity or your cart contains items from another restaurant!"
            );
            return false;
        }

        System.out.println("Item added to cart!");

        return true;
    }

    public List<CartItem> getCartItems() {
        return cart.getItems();
    }

    public double getTotal() {
        return cart.getTotal();
    }

    public boolean isEmpty() {
        return cart.isEmpty();
    }

    public String getRestaurantName() {
        return cart.getRestaurantName();
    }

    public Cart getCart() {
        return cart;
    }

    public void clearCart() {
        cart.clear();
    }
}