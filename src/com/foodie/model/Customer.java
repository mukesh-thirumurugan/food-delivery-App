package com.foodie.model;

public class Customer {

    private int customerId;
    private String name;
    private String email;
    private String password;

    public Customer(int customerId, String name, String email, String password) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId
                + "\nName: " + name
                + "\nEmail: " + email;
    }
}