package org.tvolkov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tvolkov.model.Invoice;
import org.tvolkov.repository.InvoiceRepository;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public String getInvoicesPerMonth(long customerId, int month, String filter){
        return invoiceRepository.findOne(1l).toString();
    }

    public String getInvoicesPerAddress(long customerId, String addressId){
        return invoiceRepository.findOne(1l).toString();
    }

    public String getFullInvoicesHistory(long customerId){
        return invoiceRepository.findOne(1l).toString();
    }

    public String generateInvoice(Invoice invoice){
        return invoiceRepository.save(invoice).toString();
    }
}
