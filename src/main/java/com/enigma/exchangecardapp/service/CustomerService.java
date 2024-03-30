package com.enigma.exchangecardapp.service;


import com.enigma.exchangecardapp.model.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer create(Customer customer);
    Customer getById(String id);
    List<Customer> getAll();
    Customer update(Customer customer);
    void delete(String id);
    Optional<Customer> getCustomerByUserId(String userId);
}
