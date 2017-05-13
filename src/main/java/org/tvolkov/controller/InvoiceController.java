package org.tvolkov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tvolkov.model.InvoiceType;
import org.tvolkov.service.InvoiceService;
import org.tvolkov.model.Invoice;

@RestController
@RequestMapping("/api/v1.0/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping(params = {"customerId", "month"})
    public String getInvoicesPerMonth(@RequestParam("customerId") int customerId,
                                      @RequestParam("month") int month){
        return invoiceService.getInvoicesPerMonth(customerId, month, "all");
    }

    @GetMapping(params = {"customerId", "filter", "month"})
    public String getShopInvoicesPerMonth(@RequestParam("customerId") int customerId,
                                          @RequestParam("filter") String filter,
                                          @RequestParam("month") int month){
        return invoiceService.getInvoicesPerMonth(customerId, month, filter);
    }

    @GetMapping(params = {"customerId", "addressId"})
    public String getInvoicesHistoryPerAddress(@RequestParam("customerId") int customerId,
                                               @RequestParam("addressId") int addressId){
        return invoiceService.getInvoicesPerAddress(customerId, addressId);
    }

    @GetMapping(params = "customerId")
    public String getFullInvoicesHistory(@RequestParam("customerId") int customerId){
        return invoiceService.getFullInvoicesHistory(customerId);
    }

    @PostMapping
    public String generateInvoice(){
        return invoiceService.generateInvoice(new Invoice(InvoiceType.advancePayment, 20.99));
    }
}
