package org.tvolkov.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.persistence.*;
import java.time.YearMonth;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Convert(converter = InvoiceTypeConverter.class)
    private InvoiceType invoiceType;

    private Double amount;

    @ManyToOne
    @JoinColumn(name = "address_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Address address;

    @Convert(converter = MonthConverter.class)
    @JsonSerialize(using = ToStringSerializer.class)
    private YearMonth month;

    protected Invoice(){}

    public void setId(int id){
        this.id = id;
    }

    public Invoice(InvoiceType invoiceType, Double amount, Address address, YearMonth month){
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

    public int getId(){
        return this.id == null ? 0 : this.id;
    }

    public YearMonth getMonth() {
        return month;
    }

    public void setMonth(YearMonth month) {
        this.month = month;
    }

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;

        Invoice invoice = (Invoice) o;

        if (id != invoice.id) return false;
        if (invoiceType != invoice.invoiceType) return false;
        if (Double.compare(invoice.amount, amount) != 0) return false;
        if (month != invoice.month) return false;
        return address.equals(invoice.address);
    }
}