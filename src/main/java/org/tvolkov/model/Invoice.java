package org.tvolkov.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String invoiceId;
    private InvoiceType invoiceType;
    private double amount;

    protected Invoice(){}

    public Invoice(InvoiceType invoiceType, double amount){
        this.invoiceType = invoiceType;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId='" + invoiceId + '\'' +
                ", invoiceType=" + invoiceType +
                ", amount=" + amount +
                '}';
    }

    public enum InvoiceType {
        advancePayment, shopPurchase
    }
}
