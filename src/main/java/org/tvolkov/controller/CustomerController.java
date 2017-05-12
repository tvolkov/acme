package org.tvolkov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tvolkov.repository.CustomerRepository;

import java.util.Arrays;

@RestController
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/api/v1.0/customers")
    public String getAllCustomers(){
        return Arrays.toString(customerRepository.findAll().toArray());
    }
}
