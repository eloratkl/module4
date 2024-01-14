package com.ntu.edu.group5.ecommerce;

// This may not be auto-imported
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc // This is needed to autowire the MockMvc object
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    // Add code here
    @DisplayName("Get product by Id")
    @Test
    public void testProductIdPresent() throws Exception {
        // Step 1:Build a GET request to /products/1
        RequestBuilder request = MockMvcRequestBuilders.get("/products/1");

        // Step 2: Perform the request, get teh response and assert
        mockMvc.perform(request)
                // Assert that the status code is 200
                .andExpect(status().isOk())
                // Assert that the content type is JSON
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Assert that the id returned is 1
                .andExpect(jsonPath("$.id").value(1));

    }

    @DisplayName("Product Id is not found")
    @Test
    public void productIdNotFound() throws Exception {
        // Step 1:Build a GET request to /products/100
        RequestBuilder request = MockMvcRequestBuilders.get("/products/100");

        // Step 2: Perform the request, get teh response and assert
        mockMvc.perform(request)
                // Assert that the status code is 404
                .andExpect(status().isNotFound());

    }

}
