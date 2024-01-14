package com.ntu.edu.group5.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ntu.edu.group5.ecommerce.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
    
}
