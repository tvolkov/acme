package org.tvolkov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tvolkov.model.Invoice;
import org.tvolkov.model.InvoiceType;
import org.tvolkov.repository.InvoiceRepository;

import java.time.Month;
import java.util.Arrays;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public String getInvoicesPerMonth(int customerId, int month, String type){
        int realMonth = Month.of(month).ordinal() + 1; // +1 needed because Month's ordinals are zero-based and we are useing one-based months notation
        if (type == null){
            return invoiceRepository.findByCustomerIdAndMonth(customerId, realMonth).toString();
        }
        return invoiceRepository.findByCustomerIdMonthAndType(customerId, realMonth, InvoiceType.valueOf(type).ordinal()).toString();
    }

    public String getInvoicesPerAddress(int customerId, int addressId){
        return invoiceRepository.findByCustomerIdAndAddressId(customerId, addressId).toString();
    }

    public String getFullInvoicesHistory(int customerId){
        return Arrays.toString(invoiceRepository.findByCustomerId(customerId).toArray());
    }

    public String generateInvoice(Invoice invoice){
        return invoiceRepository.save(invoice).toString();
    }
}
