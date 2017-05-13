package org.tvolkov.model;

import javax.persistence.*;
import java.time.Month;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.ORDINAL)
    private InvoiceType invoiceType;

    private double amount;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Enumerated(EnumType.ORDINAL)
    private Month month;

    protected Invoice(){}

    public Invoice(InvoiceType invoiceType, double amount){
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

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }
}