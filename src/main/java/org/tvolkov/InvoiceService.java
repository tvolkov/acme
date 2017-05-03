package org.tvolkov;

import org.springframework.stereotype.Service;
import org.tvolkov.model.Invoice;

@Service
public class InvoiceService {

    public String getInvoicesPerMonth(long customerId, int month, String filter){

    }

    public String getInvoicesHistoryPerAddress(long customerId, String addressId){

    }

    public String getFullInvoicesHistory(long customerId){

    }

    public String generateInvoice(Invoice invoice){

    }
}
