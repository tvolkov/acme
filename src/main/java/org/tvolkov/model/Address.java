package org.tvolkov.model;

import javax.persistence.*;

@Entity
public class Address {

    public int getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String address;

    @OneToOne(mappedBy = "address")
    private Invoice invoice;

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

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
