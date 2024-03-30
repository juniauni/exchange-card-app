package com.enigma.exchangecardapp.controller;


import com.enigma.exchangecardapp.model.entity.Customer;
import com.enigma.exchangecardapp.model.response.CommonResponse;
import com.enigma.exchangecardapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/customers")
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        Customer customerResponse = customerService.create(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfully created new customer!")
                .data(customerResponse)
                .build());
    }
    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Customer> customerList = customerService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.<List<Customer>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully fetch customer!")
                        .data(customerList)
                        .build());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable String id){
        Customer customerResponse = customerService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully fetch customer!")
                .data(customerResponse)
                .build());
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer){
        Customer customerResponse = customerService.update(customer);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully update customer!")
                .data(customerResponse)
                .build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String id){
        customerService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
