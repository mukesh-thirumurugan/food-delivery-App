package com.foodie.cli;

import java.util.List;
import java.util.Scanner;

import com.foodie.model.CartItem;
import com.foodie.model.Customer;
import com.foodie.model.MenuItem;
import com.foodie.model.Order;
import com.foodie.model.Restaurant;

import com.foodie.repository.CustomerRepository;
import com.foodie.repository.OrderRepository;
import com.foodie.repository.RestaurantRepository;

import com.foodie.service.CartService;
import com.foodie.service.CustomerService;
import com.foodie.service.OrderService;
import com.foodie.service.RestaurantService;

public class FoodieCLI {

    private Scanner scanner = new Scanner(System.in);

    private CustomerService customerService;
    private RestaurantService restaurantService;
    private CartService cartService;
    private OrderService orderService;

    public FoodieCLI() {

        CustomerRepository customerRepository =
                new CustomerRepository();

        RestaurantRepository restaurantRepository =
                new RestaurantRepository();

        OrderRepository orderRepository =
                new OrderRepository();

        customerService =
                new CustomerService(customerRepository);

        restaurantService =
                new RestaurantService(restaurantRepository);

        cartService =
                new CartService();

        orderService =
                new OrderService(orderRepository);
    }

    public void start() {

        boolean running = true;

        while (running) {

            System.out.println("\n============================");
            System.out.println("      WELCOME TO FOODIE");
            System.out.println("============================");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    register();
                    break;

                case 2:
                    Customer customer = login();

                    if (customer != null) {
                        customerMenu(customer);
                    }

                    break;

                case 3:
                    running = false;
                    System.out.println("Thank you for using Foodie!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }

        scanner.close();
    }

    private void register() {

        System.out.println("\n--- CUSTOMER REGISTRATION ---");

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        Customer customer =
                customerService.registerCustomer(name, email, password);

        if (customer != null) {
            System.out.println(customer);
        }
    }

    private Customer login() {

        System.out.println("\n--- CUSTOMER LOGIN ---");

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        return customerService.login(email, password);
    }

    private void customerMenu(Customer customer) {

        boolean loggedIn = true;

        while (loggedIn) {

            System.out.println("\n============================");
            System.out.println("Welcome, " + customer.getName());
            System.out.println("============================");

            System.out.println("1. View Restaurants");
            System.out.println("2. View Cart");
            System.out.println("3. Place Order");
            System.out.println("4. View Order History");
            System.out.println("5. Logout");

            int choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    showRestaurants();
                    break;

                case 2:
                    viewCart();
                    break;

                case 3:
                    placeOrder(customer);
                    break;

                case 4:
                    viewOrderHistory(customer);
                    break;

                case 5:
                    loggedIn = false;
                    cartService.clearCart();
                    System.out.println("Logged out successfully!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void showRestaurants() {

        System.out.println("\n--- RESTAURANTS ---");

        List<Restaurant> restaurants =
                restaurantService.getAllRestaurants();

        for (Restaurant restaurant : restaurants) {
            System.out.println(restaurant);
        }

        int restaurantId =
                readInt("Enter restaurant ID (0 to go back): ");

        if (restaurantId == 0) {
            return;
        }

        Restaurant restaurant =
                restaurantService.getRestaurantById(restaurantId);

        if (restaurant == null) {
            System.out.println("Restaurant not found!");
            return;
        }

        showMenu(restaurant);
    }

    private void showMenu(Restaurant restaurant) {

        boolean browsing = true;

        while (browsing) {

            System.out.println("\n--- " + restaurant.getName() + " MENU ---");

            List<MenuItem> menuItems = restaurant.getMenuItems();

            for (MenuItem item : menuItems) {
                System.out.println(item);
            }

            System.out.println("0. Back");

            int itemId = readInt("Enter food item ID: ");

            if (itemId == 0) {
                browsing = false;
                continue;
            }

            MenuItem selectedItem = null;

            for (MenuItem item : menuItems) {

                if (item.getItemId() == itemId) {
                    selectedItem = item;
                    break;
                }
            }

            if (selectedItem == null) {
                System.out.println("Food item not found!");
                continue;
            }

            int quantity = readInt("Enter quantity: ");

            cartService.addItem(restaurant, selectedItem, quantity);
        }
    }

    private void viewCart() {

        System.out.println("\n--- YOUR CART ---");

        if (cartService.isEmpty()) {
            System.out.println("Your cart is empty!");
            return;
        }

        System.out.println("Restaurant: " + cartService.getRestaurantName());

        for (CartItem item : cartService.getCartItems()) {
            System.out.println(item);
        }

        System.out.println("----------------------------");
        System.out.println("Total: Rs. " + cartService.getTotal());
    }

    private void placeOrder(Customer customer) {

        System.out.println("\n--- PLACE ORDER ---");

        if (cartService.isEmpty()) {
            System.out.println("Your cart is empty!");
            return;
        }

        viewCart();

        System.out.println("1. Confirm Order");
        System.out.println("2. Cancel");

        int choice = readInt("Enter your choice: ");

        if (choice == 1) {

            Order order = orderService.placeOrder(
                    customer,
                    cartService.getCart()
            );

            if (order != null) {
                System.out.println(order);
            }

        } else {
            System.out.println("Order cancelled.");
        }
    }

    private void viewOrderHistory(Customer customer) {

        System.out.println("\n--- ORDER HISTORY ---");

        List<Order> orders =
                orderService.getCustomerOrders(customer.getCustomerId());

        if (orders.isEmpty()) {
            System.out.println("No orders found!");
            return;
        }

        for (Order order : orders) {
            System.out.println(order);
            System.out.println("----------------------------");
        }
    }

    private int readInt(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }
}