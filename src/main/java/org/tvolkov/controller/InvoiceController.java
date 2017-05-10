package org.tvolkov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tvolkov.InvoiceService;
import org.tvolkov.model.Invoice;

@RestController
@RequestMapping("/api/v1.0/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping(method = RequestMethod.GET, params = "customerId, month")
    public String getInvoicesPerMonth(@RequestParam("customerId") long customerId,
                                      @RequestParam("month") int month){
        return invoiceService.getInvoicesPerMonth(customerId, month, "all");
    }

    @RequestMapping(method = RequestMethod.GET, params = "customerId, filter, month")
    public String getShopInvoicesPerMonth(@RequestParam("customerId") long customerId,
                                          @RequestParam("filter") String filter,
                                          @RequestParam("month") int month){
        return invoiceService.getInvoicesPerMonth(customerId, month, filter);
    }

    @RequestMapping(method = RequestMethod.GET, params = "customerId, addressId")
    public String getInvoicesHistoryPerAddress(@RequestParam("customerId") long customerId,
                                               @RequestParam("addressId") String addressId){
        return invoiceService.getInvoicesPerAddress(customerId, addressId);
    }

    @RequestMapping(method = RequestMethod.GET, params = "customerId")
    public String getFullInvoicesHistory(@RequestParam("customerId") long customerId){
        return invoiceService.getFullInvoicesHistory(customerId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String generateInvoice(){
        return invoiceService.generateInvoice(new Invoice(Invoice.InvoiceType.advancePayment, 20.99));
    }
}
