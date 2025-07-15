package com.sahu.sagachoreography.order.service;

import com.sahu.sagachoreography.common.dto.OrderRequestDTO;
import com.sahu.sagachoreography.common.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    OrderResponseDTO placeOrder(OrderRequestDTO orderRequestDTO);
    List<OrderResponseDTO> fetchAllOrders();
}
