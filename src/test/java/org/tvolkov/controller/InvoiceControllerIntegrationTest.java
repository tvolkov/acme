package org.tvolkov.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.tvolkov.model.Invoice;
import org.tvolkov.model.InvoiceType;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class InvoiceControllerIntegrationTest {

    private static final String API_PATH = "/api/v1.0/invoices";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getInvoicesByCustomerIdAndMonth(){
        ResponseEntity<List<Invoice>> responseEntity = restTemplate.exchange(API_PATH + "?customerId=21&month=1",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Invoice>>(){});

        List<Invoice> result = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, result.size());
        Invoice invoice = result.get(0);
        assertEquals(31, invoice.getId());
        assertEquals(InvoiceType.advancePayment, invoice.getInvoiceType());
        assertEquals(20.99, invoice.getAmount(), 0);
        assertEquals(1, invoice.getMonth());
        assertEquals(11, invoice.getAddress().getId());
    }

    @Test
    public void getInvoicesByCustomerIdFilterAndMonth(){
        ResponseEntity<List<Invoice>> responseEntity = restTemplate.exchange(API_PATH + "?customerId=21&filter=shopPurchase&month=3",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Invoice>>(){});

        List<Invoice> result = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, result.size());
        Invoice invoice = result.get(0);
        assertEquals(33, invoice.getId());
        assertEquals(InvoiceType.shopPurchase, invoice.getInvoiceType());
        assertEquals(99.9, invoice.getAmount(), 0);
        assertEquals(3, invoice.getMonth());
        assertEquals(13, invoice.getAddress().getId());
    }

    @Test
    public void getInvoicesByCustomerIdAndAddress(){
        ResponseEntity<List<Invoice>> responseEntity = restTemplate.exchange(API_PATH + "?customerId=22&addressId=15",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Invoice>>(){});

        List<Invoice> result = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(7, result.size());
        Invoice invoice = result.get(0);
        assertEquals(36, invoice.getId());
        assertEquals(InvoiceType.advancePayment, invoice.getInvoiceType());
        assertEquals(99.9, invoice.getAmount(), 0);
        assertEquals(6, invoice.getMonth());
        assertEquals(15, invoice.getAddress().getId());
    }

    @Test
    public void getInvoicesByCustomerId(){
        ResponseEntity<List<Invoice>> responseEntity = restTemplate.exchange(API_PATH + "?customerId=21",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Invoice>>(){});

        List<Invoice> result = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(3, result.size());
        Invoice invoice = result.get(0);
        assertEquals(31, invoice.getId());
        assertEquals(InvoiceType.advancePayment, invoice.getInvoiceType());
        assertEquals(20.99, invoice.getAmount(), 0);
        assertEquals(1, invoice.getMonth());
        assertEquals(11, invoice.getAddress().getId());
    }

    @Test
    public void generateInvoice() throws URISyntaxException {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.set("Content-Type", "application/json");
        RequestEntity<String> requestEntity = new RequestEntity<>("{\"invoiceType\" : \"shopPurchase\",\"amount\" : 79.9,\"address\" : 15,\"month\" : 6}", headers, HttpMethod.POST, new URI(API_PATH));
        ResponseEntity<Invoice> responseEntity = restTemplate.postForEntity(API_PATH, requestEntity , Invoice.class);
        Invoice invoice = responseEntity.getBody();
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(313, invoice.getId());
        assertEquals(InvoiceType.shopPurchase, invoice.getInvoiceType());
        assertEquals(79.9, invoice.getAmount(), 0);
        assertEquals(6, invoice.getMonth());
        assertEquals(15, invoice.getAddress().getId());
    }
}
