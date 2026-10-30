package com.foodie.service;

import com.foodie.model.Customer;
import com.foodie.repository.CustomerRepository;

public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer registerCustomer(
            String name,
            String email,
            String password) {

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Name cannot be empty!");
            return null;
        }

        if (email == null || !email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            System.out.println("Invalid email!");
            return null;
        }

        if (password == null || password.length() < 8) {
            System.out.println("Password must contain at least 8 characters!");
            return null;
        }

        if (customerRepository.existsByEmail(email)) {
            System.out.println("Email already registered!");
            return null;
        }

        int customerId = customerRepository.count() + 1;

        Customer customer = new Customer(
                customerId,
                name.trim(),
                email.trim(),
                password
        );

        customerRepository.save(customer);

        System.out.println("Registration successful!");

        return customer;
    }

    public Customer login(String email, String password) {

        Customer customer = customerRepository.findByEmail(email);

        if (customer == null) {
            System.out.println("Account not found!");
            return null;
        }

        if (!customer.checkPassword(password)) {
            System.out.println("Incorrect password!");
            return null;
        }

        System.out.println("Login successful!");

        return customer;
    }
}