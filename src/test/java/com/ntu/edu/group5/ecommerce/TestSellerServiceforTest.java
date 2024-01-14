package com.ntu.edu.group5.ecommerce;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ntu.edu.group5.ecommerce.service.SellerServiceforTest;

/*
 * Note that you should not accidentally import JUnit v4. All codes are using JUnit v5.
 */

@SpringBootTest
public class TestSellerServiceforTest {

	@Autowired
	SellerServiceforTest service;

	// Add code here
	@Test
	public void testVerifyPasswordLengthIs8() throws Exception {
		assertEquals(8, service.verifyPassword("password"));
	}

	@Test
	public void testVerifyPasswordLengthIs9() throws Exception {
		assertEquals(9, service.verifyPassword("password1"));
	}

	@Test
	public void testVerifyPasswordLengthLessThan8() throws Exception {
		assertThrows(Exception.class, () -> service.verifyPassword("passwo"));
	}

}
