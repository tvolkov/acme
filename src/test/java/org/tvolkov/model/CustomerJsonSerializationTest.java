package org.tvolkov.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class CustomerJsonSerializationTest {

    private static final String EXPECTED_JSON = "{\"id\" : 1,\"addresses\" : [ 11, 12, 13 ],\"name\" : \"customerName\"}";

    private JacksonTester<Customer> json;

    @Before
    public void setup() {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void serializeJson() throws IOException {
        Customer customer = new Customer(1, "customerName");
        customer.setAddresses(new HashSet<Address>(){{
            add(new Address(11));
            add(new Address(12));
            add(new Address(13));
        }});

        assertThat(this.json.write(customer)).isEqualToJson(EXPECTED_JSON);
    }
}