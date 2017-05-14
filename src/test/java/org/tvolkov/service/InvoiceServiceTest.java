package org.tvolkov.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.tvolkov.model.Address;
import org.tvolkov.model.Invoice;
import org.tvolkov.model.InvoiceType;
import org.tvolkov.repository.AddressRepository;
import org.tvolkov.repository.InvoiceRepository;

import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceTest {

    @InjectMocks
    private InvoiceService invoiceService;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private AddressRepository addressRepository;

    @Test
    public void shouldGetInvoicesPerMonth() throws InvalidMonthException {
        //given
        Invoice invoice = new Invoice(InvoiceType.advancePayment, 1.0, new Address(1), YearMonth.of(2017, 1));
        when(invoiceRepository.findByCustomerIdAndMonth(1, YearMonth.of(2017, 1)))
                .thenReturn(new ArrayList<Invoice>(){{add(invoice);}});

        //when
        List<Invoice> result = invoiceService.getInvoicesPerMonth(1, "2017-01", InvoiceService.NO_FILTER);

        //then
        assertEquals(1, result.size());
        assertEquals(invoice, result.get(0));
    }

    @Test
    public void shouldGetInvoicePerMonthWithInvoiceTypeSpeficied() throws InvalidMonthException {
        //given
        Invoice invoice = new Invoice(InvoiceType.advancePayment, 1.0, new Address(1), YearMonth.of(2017, 1));
        when(invoiceRepository.findByCustomerIdMonthAndType(1, YearMonth.of(2017, 1), InvoiceType.advancePayment))
                .thenReturn(new ArrayList<Invoice>(){{add(invoice);}});

        //when
        List<Invoice> result = invoiceService.getInvoicesPerMonth(1, "2017-01", "advancePayment");

        //then
        assertEquals(1, result.size());
        assertEquals(invoice, result.get(0));

    }

    @Test(expected = DateTimeParseException.class)
    public void shouldThrowAnExceptionWhenGettingInvoicePerMonthIfMonthIsOutOfRange() throws InvalidMonthException {
        //when
        invoiceService.getInvoicesPerMonth(1, "qwe", null);
    }

    @Test(expected = DateTimeParseException.class)
    public void shouldThrowAnExceptionWhenGettingInvoicePerMonthWithTypeIfMonthIsOutOfRange() throws InvalidMonthException {
        //when
        invoiceService.getInvoicesPerMonth(1, "qwe", "advancePayment");
    }

    @Test
    public void shouldGetInvoicesPerAddress(){
        //given
        Invoice invoice = new Invoice(InvoiceType.advancePayment, 1.0, new Address(1), YearMonth.of(2017, 1));
        when(invoiceRepository.findByCustomerIdAndAddressId(1, 1))
                .thenReturn(new ArrayList<Invoice>(){{add(invoice);}});

        //when
        List<Invoice> result = invoiceService.getInvoicesPerAddress(1, 1);

        //then
        assertEquals(1, result.size());
        assertEquals(invoice, result.get(0));
    }

    @Test
    public void shouldGetFullHistoryOfInvoices(){
        //given
        Invoice invoice1 = new Invoice(InvoiceType.advancePayment, 1.0, new Address(1), YearMonth.of(2017, 1));
        Invoice invoice2 = new Invoice(InvoiceType.advancePayment, 1.0, new Address(1), YearMonth.of(2017, 1));
        when(invoiceRepository.findByCustomerId(1))
                .thenReturn(new ArrayList<Invoice>(){{add(invoice1); add(invoice2);}});

        //when
        List<Invoice> result = invoiceService.getFullInvoicesHistory(1);

        //then
        assertEquals(2, result.size());
        assertTrue(result.contains(invoice1));
        assertTrue(result.contains(invoice2));
    }

    @Test
    public void shouldGenerateInvoice() throws InvalidAddressIdException {
        //given
        Address address = new Address(1);
        Invoice invoice = new Invoice(InvoiceType.advancePayment, 1.0, address, YearMonth.of(2017, 1));
        when(addressRepository.findOne(1)).thenReturn(address);
        when(invoiceRepository.save(eq(invoice))).thenReturn(invoice);

        //when
        Invoice savedInvoice = invoiceService.generateInvoice(invoice);

        //then
        assertEquals(invoice, savedInvoice);
        assertEquals(address, savedInvoice.getAddress());
    }

    @Test(expected = InvalidAddressIdException.class)
    public void shouldThrowAnExceptionWhenGeneratingInvoiceIfAddressIdIsUnknown() throws InvalidAddressIdException {
        //given
        Address address = new Address(1);
        Invoice invoice = new Invoice(InvoiceType.advancePayment, 1.0, address, YearMonth.of(2017, 1));
        when(invoiceRepository.save(invoice)).thenReturn(invoice);

        //when
        Invoice savedInvoice = invoiceService.generateInvoice(invoice);
    }
}