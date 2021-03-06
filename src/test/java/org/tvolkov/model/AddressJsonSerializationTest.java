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
public class AddressJsonSerializationTest {

    private static final String EXPECTED_JSON = " {\"id\":1,\"customer\":12,\"address\":\"MyAddress\"}";

    private JacksonTester<Address> json;

    @Before
    public void setup() {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void serializeJson() throws IOException {
        Address address = new Address(1);
        address.setCustomer(new Customer(12, "customer"));
        address.setAddress("MyAddress");

        assertThat(this.json.write(address)).isStrictlyEqualToJson(EXPECTED_JSON);
    }
}