package com.foodie.repository;

import java.util.ArrayList;
import java.util.List;

import com.foodie.model.Customer;

public class CustomerRepository {

    private List<Customer> customers = new ArrayList<>();

    public void save(Customer customer) {
        customers.add(customer);
    }

    public Customer findByEmail(String email) {

        for (Customer customer : customers) {

            if (customer.getEmail().equalsIgnoreCase(email)) {
                return customer;
            }
        }

        return null;
    }

    public boolean existsByEmail(String email) {
        return findByEmail(email) != null;
    }

    public List<Customer> findAll() {
        return new ArrayList<>(customers);
    }

    public int count() {
        return customers.size();
    }
}