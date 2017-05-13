package org.tvolkov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tvolkov.model.Address;
import org.tvolkov.repository.AddressRepository;

import java.util.List;

/**
 * This controller was addede for debugging purposes
 */
@RestController
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/api/v1.0/addresses")
    public ResponseEntity<List<Address>> getAllAddresses(){
        return new ResponseEntity<>(addressRepository.findAll(), HttpStatus.OK);
    }
}
