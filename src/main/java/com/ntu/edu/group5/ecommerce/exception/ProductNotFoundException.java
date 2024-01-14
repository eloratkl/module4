package com.ntu.edu.group5.ecommerce.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long productId) {
        super("Could not find product with id: " + productId + ".");
    }
}
