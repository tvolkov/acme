package org.tvolkov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tvolkov.model.Invoice;
import org.tvolkov.service.InvalidAddressIdException;
import org.tvolkov.service.InvoiceService;

import java.time.DateTimeException;
import java.util.List;

@RestController
@RequestMapping("/api/v1.0/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping(params = {"customerId", "month"})
    public ResponseEntity<List<Invoice>> getAllInvoicesPerMonth(@RequestParam("customerId") int customerId,
                                                                @RequestParam("month") String month){
            return new ResponseEntity<>(invoiceService.getInvoicesPerMonth(customerId, month, InvoiceService.NO_FILTER), HttpStatus.OK);
    }

    @GetMapping(params = {"customerId", "filter", "month"})
    public ResponseEntity<List<Invoice>> getShopInvoicesPerMonth(@RequestParam("customerId") int customerId,
                                                                 @RequestParam("month") String month,
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
            return new ResponseEntity<>(invoiceService.generateInvoice(invoice), HttpStatus.CREATED);
        } catch (InvalidAddressIdException e) {
            return new ResponseEntity<>((Invoice) null, HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(DateTimeException.class)
    public ResponseEntity<Invoice> handleDateTimeException(DateTimeException dateTimeException){
        return new ResponseEntity<>((Invoice)null, HttpStatus.BAD_REQUEST);
    }
}
