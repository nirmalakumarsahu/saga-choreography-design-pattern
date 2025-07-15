package com.sahu.sagachoreography.order.repository;

import com.sahu.sagachoreography.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
