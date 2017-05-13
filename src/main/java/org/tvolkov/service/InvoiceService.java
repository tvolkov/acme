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
        return invoiceRepository.findByCustomerIdMonthAndType(customerId, Month.of(month), InvoiceType.valueOf(type)).toString();
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
