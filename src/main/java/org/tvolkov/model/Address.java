package org.tvolkov.model;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String address;

    protected Address(){}

    public Address(String address, Customer customer){
        this.address = address;
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", customer=" + customer.getId() +
                ", address='" + address + '\'' +
                '}';
    }
}
