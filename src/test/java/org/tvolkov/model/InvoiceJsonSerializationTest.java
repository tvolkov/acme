package org.tvolkov.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class InvoiceJsonSerializationTest {

    private static final String EXPECTED_JSON = "{\"invoiceType\" : 0,\"amount\" : 20.99,\"month\" : 1,\"address\" : 11}";

    private JacksonTester<Invoice> json;

    @Before
    public void setup() {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void serializeJson() throws IOException {
        Invoice invoice = new Invoice(0, 20.99, new Address(11), 1);
        assertThat(this.json.write(invoice)).isEqualToJson(EXPECTED_JSON);
    }
}