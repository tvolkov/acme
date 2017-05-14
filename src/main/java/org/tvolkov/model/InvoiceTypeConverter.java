package org.tvolkov.model;

import javax.persistence.AttributeConverter;

public class InvoiceTypeConverter implements AttributeConverter<InvoiceType, String> {
    @Override
    public String convertToDatabaseColumn(InvoiceType attribute) {
        switch (attribute){
            case advancePayment:
                return "a";
            case shopPurchase:
                return "s";
            default:
                throw new IllegalArgumentException("Unknown invoice type: " + attribute);
        }
    }

    @Override
    public InvoiceType convertToEntityAttribute(String dbData) {
        switch (dbData){
            case "a":
                return InvoiceType.advancePayment;
            case "s":
                return InvoiceType.shopPurchase;
            default:
                throw new IllegalArgumentException("Unknown invoice type: " + dbData);
        }
    }
}
