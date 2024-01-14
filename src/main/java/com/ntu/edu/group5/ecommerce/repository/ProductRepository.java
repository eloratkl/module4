package com.ntu.edu.group5.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ntu.edu.group5.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
}
