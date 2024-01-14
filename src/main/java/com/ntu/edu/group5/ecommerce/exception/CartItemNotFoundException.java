package com.ntu.edu.group5.ecommerce.exception;

public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException(Long id) {
        super("Could not find cartItem with id: " + id + ".");
    }

}
