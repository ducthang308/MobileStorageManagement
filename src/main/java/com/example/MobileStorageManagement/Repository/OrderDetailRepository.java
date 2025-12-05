package com.example.MobileStorageManagement.Repository;

import com.example.MobileStorageManagement.Entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
    List<OrderDetail> findByOrder_OrderID(Long orderID);
}
