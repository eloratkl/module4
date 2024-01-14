package com.ntu.edu.group5.ecommerce.exception;

public class SellerNotFoundException extends RuntimeException {
    public SellerNotFoundException(Long id) {
        super("Could not find seller with id: " + id + ".");
    }
}
