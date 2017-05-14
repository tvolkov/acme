package org.tvolkov.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.tvolkov.model.Address;
import org.tvolkov.model.Invoice;
import org.tvolkov.model.InvoiceType;
import org.tvolkov.service.InvalidAddressIdException;
import org.tvolkov.service.InvalidMonthException;
import org.tvolkov.service.InvoiceService;

import java.util.ArrayList;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
@AutoConfigureMockMvc
public class InvoiceControllerTest {

    private static final String API_PATH = "/api/v1.0/invoices";
    private static final String CUSTOMER_ID_PARAM = "customerId";
    private static final String MONTH_PARAM = "month";
    private static final String FILTER_PARAM = "filter";
    private static final String ADDRESS_ID_PARAM = "addressId";
    private static final String INVOICE_JSON = "{\"id\":0,\"invoiceType\":0,\"amount\":1.0,\"month\":1,\"address\":123}";
    private static final String INVOICE_JSON_ARRAY = "[" + INVOICE_JSON + "]";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private InvoiceService invoiceService;

    @Test
    public void shouldReturnInvoicePerMonth() throws Exception {
        given(invoiceService.getInvoicesPerMonth(1, 1, InvoiceService.NO_FILTER))
                .willReturn(new ArrayList<Invoice>(){{add(new Invoice(0, 1.0, new Address(123), 1));}});

        mvc.perform(MockMvcRequestBuilders.get(API_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .param(CUSTOMER_ID_PARAM, "1")
                .param(MONTH_PARAM, "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(INVOICE_JSON_ARRAY)));
    }

    @Test
    public void shouldReturnErrorIfMonthIsOutOfRangeWhenRequestingInvoicesPerMonth() throws Exception {
        given(invoiceService.getInvoicesPerMonth(1, 1, InvoiceService.NO_FILTER)).willThrow(InvalidMonthException.class);

        mvc.perform(MockMvcRequestBuilders.get(API_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .param(CUSTOMER_ID_PARAM, "1")
                .param(MONTH_PARAM, "1"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldReturnShopInvoicePerMonth() throws Exception {
        given(invoiceService.getInvoicesPerMonth(1, 1, InvoiceType.shopPurchase.toString()))
                .willReturn(new ArrayList<Invoice>(){{add(new Invoice(0, 1.0, new Address(123), 1));}});

        mvc.perform(MockMvcRequestBuilders.get(API_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .param(CUSTOMER_ID_PARAM, "1")
                .param(MONTH_PARAM, "1")
                .param(FILTER_PARAM, InvoiceType.shopPurchase.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(INVOICE_JSON_ARRAY)));
    }

    @Test
    public void shouldReturnErrorIfMonthIsOutOfRangeWhenRequestingFilteredInvoicesPerMonth() throws Exception {
        given(invoiceService.getInvoicesPerMonth(1, 1, InvoiceType.shopPurchase.toString())).willThrow(InvalidMonthException.class);

        mvc.perform(MockMvcRequestBuilders.get(API_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .param(CUSTOMER_ID_PARAM, "1")
                .param(MONTH_PARAM, "1")
                .param(FILTER_PARAM, InvoiceType.shopPurchase.toString()))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldReturnInvoiceHistoryPerAddress() throws Exception {
        given(invoiceService.getInvoicesPerAddress(1, 123))
                .willReturn(new ArrayList<Invoice>(){{add(new Invoice(0, 1.0, new Address(123), 1));}});

        mvc.perform(MockMvcRequestBuilders.get(API_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .param(CUSTOMER_ID_PARAM, "1")
                .param(ADDRESS_ID_PARAM, "123"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(INVOICE_JSON_ARRAY)));
    }

    @Test
    public void shouldReturnFullInvoicesHistory() throws Exception {
        given(invoiceService.getFullInvoicesHistory(21))
                .willReturn(new ArrayList<Invoice>(){{add(new Invoice(0, 1.0, new Address(123), 1));}});

        mvc.perform(MockMvcRequestBuilders.get(API_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .param(CUSTOMER_ID_PARAM, "21"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(INVOICE_JSON_ARRAY)));
    }

    @Test
    public void shouldGenerateInvoice() throws Exception {
        Invoice invoice = new Invoice(0, 1.0, new Address(123), 1);
        given(invoiceService.generateInvoice(anyObject())).willReturn(invoice); //TODO anyObject is a workaround, need to replace it with the appropriate mather

        mvc.perform(MockMvcRequestBuilders.post(API_PATH)
                .content(INVOICE_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(INVOICE_JSON)));
    }

    @Test
    public void shouldReturnErrorIfAdressIsInvalidWhenGeneratingInvoice() throws Exception {
        Invoice invoice = new Invoice(0, 1.0, new Address(123), 1);
        given(invoiceService.generateInvoice(anyObject())).willThrow(InvalidAddressIdException.class);//TODO anyObject is a workaround, need to replace it with the appropriate mather

        mvc.perform(MockMvcRequestBuilders.post(API_PATH)
                .content(INVOICE_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }
}
//todo add integration tests