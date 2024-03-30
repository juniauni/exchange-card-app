package com.enigma.exchangecardapp.service.Impl;


import com.enigma.exchangecardapp.model.entity.Customer;
import com.enigma.exchangecardapp.repository.CustomerRepository;
import com.enigma.exchangecardapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getById(String id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer update(Customer customer) {
        Customer currentCustomer = getById(customer.getId());
        if (currentCustomer != null) {
            return customerRepository.save(customer);
        }
        return null;
    }

    @Override
    public void delete(String id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Optional<Customer> getCustomerByUserId(String userId) {
        return customerRepository.findByUserId(userId);
    }
}
