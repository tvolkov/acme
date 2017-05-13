package org.tvolkov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tvolkov.model.Invoice;
import org.tvolkov.model.InvoiceType;
import org.tvolkov.repository.InvoiceRepository;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Invoice> getInvoicesPerMonth(int customerId, int month, String type){
        int oneBasedMonth = getOneBasedMonth(month);
        if (type == null){
            return invoiceRepository.findByCustomerIdAndMonth(customerId, oneBasedMonth);
        }
        return invoiceRepository.findByCustomerIdMonthAndType(customerId, oneBasedMonth, InvoiceType.valueOf(type).ordinal());
    }

    public List<Invoice> getInvoicesPerAddress(int customerId, int addressId){
        return invoiceRepository.findByCustomerIdAndAddressId(customerId, addressId);
    }

    public List<Invoice> getFullInvoicesHistory(int customerId){
        return invoiceRepository.findByCustomerId(customerId);
    }

    public Invoice generateInvoice(Invoice invoice){
        return invoiceRepository.save(invoice);
    }

    private int getOneBasedMonth(int zeroBasedMonth){
        return Month.of(zeroBasedMonth).ordinal() + 1; // +1 needed because Month's ordinals are zero-based but we are using one-based months notation
    }
}
