package com.ntu.edu.group5.ecommerce.service;

import java.util.ArrayList;

import com.ntu.edu.group5.ecommerce.entity.Cart;

public interface CartService {
    Cart createCart(Cart cart);

    Cart getCart(Long id);

    ArrayList<Cart> getAllCarts();

    Cart updateCart(Long id, Cart cart);

    void deleteCart(Long id);
}
