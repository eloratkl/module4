package com.ntu.edu.group5.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ntu.edu.group5.ecommerce.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
