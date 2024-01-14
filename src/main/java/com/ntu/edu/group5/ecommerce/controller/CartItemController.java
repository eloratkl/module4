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

import com.ntu.edu.group5.ecommerce.entity.CartItem;
import com.ntu.edu.group5.ecommerce.service.CartItemService;
import com.ntu.edu.group5.ecommerce.service.CartItemServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cartItems")
public class CartItemController {
    private CartItemServiceImpl cartItemServiceImpl;

    public CartItemController(CartItemServiceImpl cartItemServiceImpl) {
        this.cartItemServiceImpl = cartItemServiceImpl;
    }

    // READ (GET ALL)
    @GetMapping({ "/", "" })
    public ResponseEntity<ArrayList<CartItem>> getAllCartItems() {
        ArrayList<CartItem> allCartItems = cartItemServiceImpl.getAllCartItems();
        return new ResponseEntity<>(allCartItems, HttpStatus.OK);
    }

    // READ (GET ONE)
    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartItem(@PathVariable Long id) {
        CartItem foundCartItem = cartItemServiceImpl.getCartItem(id);
        return new ResponseEntity<>(foundCartItem, HttpStatus.OK);
    }

    // CREATE
    @PostMapping({ "", "/" })
    public ResponseEntity<CartItem> createCartItem(@Valid @RequestBody DataTemplateCartItem cartItem) {
        CartItem newCartItem = cartItemServiceImpl.createCartItem(cartItem.getProductId(),
                                                                cartItem.getOrderId(),
                                                                cartItem.getCartId(),
                                                                cartItem.getQuantity());
        return new ResponseEntity<>(newCartItem, HttpStatus.CREATED);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable Long id, @RequestBody CartItem cartItem) {
        try {
            CartItem updatedCartItem = cartItemServiceImpl.updateCartItem(id, cartItem);
            return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
        } catch (RuntimeException e) {
            // not found, return 404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<CartItem> deleteCartItem(@PathVariable Long id) {
        try{
            cartItemServiceImpl.deleteCartItem(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

}

class DataTemplateCartItem {

    long productId;
    long orderId;
    long cartId;
    int quantity;

    public long getProductId() {
        return productId;
    }
    public void setProductId(long productId) {
        this.productId = productId;
    }
    public long getOrderId() {
        return orderId;
    }
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    public long getCartId() {
        return cartId;
    }
    public void setCartId(long cartId) {
        this.cartId = cartId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}