package com.ntu.edu.group5.ecommerce.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntu.edu.group5.ecommerce.entity.Cart;
import com.ntu.edu.group5.ecommerce.service.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/carts")
public class CartController {
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // READ (GET ALL)
    @GetMapping({ "/", "" })
    public ResponseEntity<ArrayList<Cart>> getAllCarts() {
        ArrayList<Cart> allCarts = cartService.getAllCarts();
        return new ResponseEntity<>(allCarts, HttpStatus.OK);
    }

    // READ (GET ONE)
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable Long id) {
        Cart foundCart = cartService.getCart(id);
        return new ResponseEntity<>(foundCart, HttpStatus.OK);
    }

    // CREATE
    @PostMapping({ "", "/" })
    public ResponseEntity<Cart> createCart(@Valid @RequestBody Cart cart) {
        Cart newCart = cartService.createCart(cart);
        return new ResponseEntity<>(newCart, HttpStatus.CREATED);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Cart cart) {
        Cart updatedCart = cartService.updateCart(id, cart);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);

    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Cart> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
