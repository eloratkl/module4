package com.ntu.edu.group5.ecommerce.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super("Could not find Order with id: " + id + ".");
    }

}
