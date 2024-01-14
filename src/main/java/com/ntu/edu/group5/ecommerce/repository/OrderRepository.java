package com.ntu.edu.group5.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ntu.edu.group5.ecommerce.entity.Order;
import java.util.Optional;


@Repository
public interface OrderRepository extends JpaRepository <Order,Long> {
    Optional<Order> findByOrderId(long orderId);
}
