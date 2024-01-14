package com.ntu.edu.group5.ecommerce.service;

import java.util.ArrayList;

import com.ntu.edu.group5.ecommerce.entity.CartItem;

public interface CartItemService {
    CartItem createCartItem(CartItem cartItem);

    CartItem getCartItem(Long id);

    ArrayList<CartItem> getAllCartItems();

    CartItem updateCartItem(Long id, CartItem cartItem);

    void deleteCartItem(Long id);

}
