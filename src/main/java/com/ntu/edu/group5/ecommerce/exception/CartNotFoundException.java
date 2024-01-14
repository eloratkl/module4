package com.ntu.edu.group5.ecommerce.exception;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(Long id) {
        super("Could not find cart with id: " + id + ".");
    }
}
