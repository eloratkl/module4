package com.ntu.edu.group5.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ntu.edu.group5.ecommerce.entity.Customer;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Custom query to find all customers with a certain first name
    List<Customer> findByFirstName(String firstName);
}
