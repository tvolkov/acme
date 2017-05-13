package org.tvolkov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tvolkov.model.Invoice;
import org.tvolkov.service.InvalidAddressIdException;
import org.tvolkov.service.InvoiceService;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping(params = {"customerId", "month"})
    public ResponseEntity<List<Invoice>> getAllInvoicesPerMonth(@RequestParam("customerId") int customerId,
                                                                @RequestParam("month") int month){
        return new ResponseEntity<>(invoiceService.getInvoicesPerMonth(customerId, month, null), HttpStatus.OK);
    }

    @GetMapping(params = {"customerId", "filter", "month"})
    public ResponseEntity<List<Invoice>> getShopInvoicesPerMonth(@RequestParam("customerId") int customerId,
                                          @RequestParam("month") int month,
                                          @RequestParam("filter") String filter){
        return new ResponseEntity<>(invoiceService.getInvoicesPerMonth(customerId, month, filter), HttpStatus.OK);
    }

    @GetMapping(params = {"customerId", "addressId"})
    public ResponseEntity<List<Invoice>> getInvoicesHistoryPerAddress(@RequestParam("customerId") int customerId,
                                               @RequestParam("addressId") int addressId){
        return new ResponseEntity<>(invoiceService.getInvoicesPerAddress(customerId, addressId), HttpStatus.OK);
    }

    @GetMapping(params = "customerId")
    public ResponseEntity<List<Invoice>> getFullInvoicesHistory(@RequestParam("customerId") int customerId){
        return new ResponseEntity<>(invoiceService.getFullInvoicesHistory(customerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Invoice> generateInvoice(@RequestBody Invoice invoice){
        try {
            return new ResponseEntity<>(invoiceService.generateInvoice(invoice), HttpStatus.OK);
        } catch (InvalidAddressIdException e) {
            return new ResponseEntity<>((Invoice) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
