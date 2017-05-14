package org.tvolkov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tvolkov.model.Address;
import org.tvolkov.model.Invoice;
import org.tvolkov.model.InvoiceType;
import org.tvolkov.repository.AddressRepository;
import org.tvolkov.repository.InvoiceRepository;

import java.time.YearMonth;
import java.util.List;

@Service
public class InvoiceService {

    public static final String NO_FILTER = "all";

    private InvoiceRepository invoiceRepository;
    private AddressRepository addressRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, AddressRepository addressRepository){
        this.invoiceRepository = invoiceRepository;
        this.addressRepository = addressRepository;
    }

    public List<Invoice> getInvoicesPerMonth(int customerId, String intYearMonth, String type) {
        YearMonth yearMonth = YearMonth.parse(intYearMonth);
        if (NO_FILTER.equals(type)){
            return invoiceRepository.findByCustomerIdAndMonth(customerId, yearMonth);
        }
        return invoiceRepository.findByCustomerIdMonthAndType(customerId, yearMonth, InvoiceType.valueOf(type));
    }

    public List<Invoice> getInvoicesPerAddress(int customerId, int addressId){
        return invoiceRepository.findByCustomerIdAndAddressId(customerId, addressId);
    }

    public List<Invoice> getFullInvoicesHistory(int customerId){
        return invoiceRepository.findByCustomerId(customerId);
    }

    public Invoice generateInvoice(Invoice invoice) throws InvalidAddressIdException {
        int addressId = invoice.getAddress().getId();
        Address address = addressRepository.findOne(addressId);
        if (address == null){
            throw new InvalidAddressIdException("No Address with id " + addressId + " exists");
        }
        return invoiceRepository.save(new Invoice(invoice.getInvoiceType(), invoice.getAmount(), address, invoice.getMonth()));
    }
}
