package com.ntu.edu.group5.ecommerce;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntu.edu.group5.ecommerce.entity.Customer;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("Get customer by Id")
    @Test
    public void getCustomerByIdTest() throws Exception {
        // Step 1:Build a GET request to /customers/1
        RequestBuilder request = MockMvcRequestBuilders.get("/customers/1");

        // Step 2: Perform the request, get the response and assert
        mockMvc.perform(request)
                // Assert that the status code is 200
                .andExpect(status().isOk())
                // Assert that the content type is JSON
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Assert that the id returned is 1
                .andExpect(jsonPath("$.id").value(1));

    }

    @DisplayName("Get all customers")
    @Test
    public void getAllCustomersTest() throws Exception {
        // Step 1:Build a GET request to /customers
        RequestBuilder request = MockMvcRequestBuilders.get("/customers");

        // Step 2: Perform the request, get the response and assert
        mockMvc.perform(request)
                // Assert that the status code is 200
                .andExpect(status().isOk())
                // Assert that the content type is JSON
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Assert that the size of the array is 10
                .andExpect(jsonPath("$.size()").value(10));

    }

    @Test
    public void validCustomerCreationTest() throws Exception {
        Customer customer = Customer.builder().firstName("Clint").lastName("Barton").email("clint@avengers.com")
                .contactNo("12345678").yearOfBirth(1975).build();

        String newCustomerAsJSON = objectMapper.writeValueAsString(customer);

        RequestBuilder request = MockMvcRequestBuilders.post("/customers").contentType(MediaType.APPLICATION_JSON)
                .content(newCustomerAsJSON);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(11))
                .andExpect(jsonPath("$.firstName").value("Clint"))
                .andExpect(jsonPath("$.lastName").value("Barton"))
                .andExpect(jsonPath("$.email").value("clint@avengers.com"))
                .andExpect(jsonPath("$.contactNo").value("12345678"))
                .andExpect(jsonPath("$.yearOfBirth").value(1975));
    }

    @Test
    public void invalidCustomerCreationTest() throws Exception {

        Customer invalidCustomer = new Customer("  ", "  ", "bruce@a.com", "12345678", 1990);

        String invalidCustomerAsJSON = objectMapper.writeValueAsString(invalidCustomer);

        RequestBuilder request = MockMvcRequestBuilders.post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidCustomerAsJSON);

        mockMvc.perform(request)
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
