package org.tvolkov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tvolkov.repository.AddressRepository;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/api/v1.0/addresses")
    public String getAllAddresses(){
        return addressRepository.findAll().stream().map(Object::toString).collect(Collectors.joining(","));
    }
}
