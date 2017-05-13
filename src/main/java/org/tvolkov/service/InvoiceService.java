package org.tvolkov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tvolkov.model.Invoice;
import org.tvolkov.repository.InvoiceRepository;

import java.util.Arrays;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public String getInvoicesPerMonth(int customerId, int month, String filter){
        return invoiceRepository.findOne(customerId).toString();
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
