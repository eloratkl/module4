package com.ntu.edu.group5.ecommerce;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntu.edu.group5.ecommerce.entity.Address;
import com.ntu.edu.group5.ecommerce.entity.Customer;
import com.ntu.edu.group5.ecommerce.service.AddressServiceImpl;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class AddressControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("Get address by Id")
    @Test
    public void getAddressByIdTest() throws Exception {
        // Step 1: Build a GET request to /addresses/1
        RequestBuilder request = MockMvcRequestBuilders.get("/addresses/1");

        // Step 2: Perform the request, get the response and assert
        mockMvc.perform(request)
                // Assert that the status code is 200
                .andExpect(status().isOk())
                // Assert that the content type is JSON
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Assert that the id returned is 1
                .andExpect(jsonPath("$.id").value(1));
    }

    @DisplayName("Get all addresses")
    @Test
    public void getAllAddressesTest() throws Exception {
        // Step 1: SETUP
        RequestBuilder request = MockMvcRequestBuilders.get("/addresses");

        // Step 2 & 3: EXECUTE and ASSERT
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(17));
    }

    @Test
    public void validAddressCreationTest() throws Exception {
        Customer customer = new Customer("John", "Doe", "johndoe@email.com",
                "12345678", 1998);
        customer.setId(12L);

        Address address = Address.builder()
                .blockNumber("123")
                .streetName("Main Street")
                .buildingName("Building A")
                .city("SG")
                .state("SG")
                .postalCode("123456")
                .customer(customer)
                .build();

        String newAddressAsJSON = objectMapper.writeValueAsString(address);

        logger.info("newAddressAsJSON: " + newAddressAsJSON);

        RequestBuilder request = MockMvcRequestBuilders.post("/addresses/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newAddressAsJSON);

        logger.info("Performing request: " + request);
        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(17))
                .andExpect(jsonPath("$.blockNumber").value("123"))
                .andExpect(jsonPath("$.streetName").value("Main Street"))
                .andExpect(jsonPath("$.buildingName").value("Building A"))
                .andExpect(jsonPath("$.city").value("SG"))
                .andExpect(jsonPath("$.state").value("SG"))
                .andExpect(jsonPath("$.postalCode").value("123456"));

        logger.info("Request performed successfully");
    }

    @Test
    public void invalidAddressCreationTest() throws Exception {

        Address invalidAddress = new Address("", "Street", "Building", "City", "State", "123456");

        String invalidAddressAsJSON = objectMapper.writeValueAsString(invalidAddress);

        RequestBuilder request = MockMvcRequestBuilders.post("/addresses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidAddressAsJSON);

        mockMvc.perform(request)
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
