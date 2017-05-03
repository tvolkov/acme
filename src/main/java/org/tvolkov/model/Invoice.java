package org.tvolkov.model;

import java.util.Date;

public class Invoice {
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

    public static enum InvoiceType {
        advancePayment, shopPurchase
    }
}
