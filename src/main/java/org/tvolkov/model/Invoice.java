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
    private String invoiceTypeLocalized;
    private Date invoiceDate;
    private Date paymentDueDate;
    private long invoiceNumber;
    private Date startDate;
    private Date endDate;
    private String periodDescription;
    private double amount;
    private double vatAmount;
    private double totalAmount;

    protected Invoice(double totalAmount){
        this.totalAmount = totalAmount;
    }

    public Invoice(InvoiceType invoiceType, String invoiceTypeLocalized, Date invoiceDate, Date paymentDueDate, long invoiceNumber, Date startDate, Date endDate, String periodDescription, double amount, double vatAmount){
        this.invoiceType = invoiceType;
        this.invoiceTypeLocalized = invoiceTypeLocalized;
        this.invoiceDate = invoiceDate;
        this.paymentDueDate = paymentDueDate;
        this.invoiceNumber = invoiceNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.periodDescription = periodDescription;
        this.amount = amount;
        this.vatAmount = vatAmount;
    }

    public static enum InvoiceType {
        advancePayment, shopPurchase
    }
}
