package org.tvolkov.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InvoiceControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldReturnInvoicePerMonth() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1.0/invoices").accept(MediaType.APPLICATION_JSON)
                .param("customerId", "1")
                .param("month", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("")));
    }

    @Test
    public void shouldReturnShopInvoicePerMonth() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1.0/invoices").accept(MediaType.APPLICATION_JSON)
                .param("customerId", "1")
                .param("month", "1")
                .param("filter", "shop"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("")));
    }

    @Test
    public void shouldReturnInvoiceHistoryPerAddress() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1.0/invoices").accept(MediaType.APPLICATION_JSON)
                .param("customerId", "1")
                .param("addressId", "8212BJ154"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("")));
    }

    @Test
    public void shouldReturnFullInvoicesHistory() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1.0/invoices").accept(MediaType.APPLICATION_JSON)
                .param("customerId", "21"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("")));
    }

    @Test
    public void shouldGenerateInvoice() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/api/v1.0/invoices").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("")));
    }
}