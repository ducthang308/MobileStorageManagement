package com.example.MobileStorageManagement.Repository;

import com.example.MobileStorageManagement.DTO.OrderResponse;
import com.example.MobileStorageManagement.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser_UserId(Integer userId);
}
