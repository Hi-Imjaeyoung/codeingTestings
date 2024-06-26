package com.example.demo.order.repository;

import com.example.demo.order.entity.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<MyOrder,Long> {
}
