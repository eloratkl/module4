package com.ntu.edu.group5.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ntu.edu.group5.ecommerce.entity.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    // Custom query to find all sellers with a certain first name
    List<Seller> findByFirstName(String firstName);
}
