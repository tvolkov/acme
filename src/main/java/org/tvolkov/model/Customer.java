package org.tvolkov.model;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Address> addresses;

    private String name;

    protected Customer(){}

    public Customer(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }
    @JsonValue
    public String getName() {
        return name;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", addresses=" + addresses +
                '}';
    }
}
