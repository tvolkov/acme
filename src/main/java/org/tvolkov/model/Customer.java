package org.tvolkov.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private List<String> addresses;

    protected Customer(){}

    public Customer(List<String> addresses){
        this.addresses = addresses;
    }
}
