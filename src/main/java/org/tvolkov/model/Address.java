package org.tvolkov.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String address;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private Set<Invoice> invoice;

    protected Address(){}

    public Address(String address, Customer customer){
        this.address = address;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getAddress() {
        return address;
    }

    public Set<Invoice> getInvoice() {
        return invoice;
    }

    public void setInvoice(Set<Invoice> invoice) {
        this.invoice = invoice;
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
