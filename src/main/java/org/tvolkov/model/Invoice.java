package org.tvolkov.model;

import javax.persistence.*;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int invoiceType;

    private double amount;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    private int month;

    protected Invoice(){}

    public Invoice(int invoiceType, double amount){
        this.invoiceType = invoiceType;
        this.amount = amount;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId='" + id + '\'' +
                ", invoiceType=" + invoiceType +
                ", amount=" + amount +
                ", address=" + address.getId() +
                '}';
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}