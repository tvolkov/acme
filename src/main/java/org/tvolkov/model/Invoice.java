package org.tvolkov.model;

import com.fasterxml.jackson.annotation.*;
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
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Address address;

    private int month;

    protected Invoice(){}

    public Invoice(int invoiceType, double amount, Address address, int month){
        this.invoiceType = invoiceType;
        this.amount = amount;
        this.address = address;
        this.month = month;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * This method is needed by jackson in order it could deserialize address
     * when generating new invoices via POST methods
     */
    @JsonProperty("address")
    public void setAddress(int addressId){
        this.address = new Address(addressId);
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

    public int getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(int invoiceType) {
        this.invoiceType = invoiceType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}