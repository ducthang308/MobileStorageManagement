package com.example.MobileStorageManagement.Repository;

import com.example.MobileStorageManagement.Entity.Order;
import com.example.MobileStorageManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUser_UserId(Integer id);
}