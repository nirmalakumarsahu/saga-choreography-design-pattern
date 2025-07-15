package com.sahu.sagachoreography.order.service.impl;

import com.sahu.sagachoreography.common.dto.OrderRequestDTO;
import com.sahu.sagachoreography.common.dto.OrderResponseDTO;
import com.sahu.sagachoreography.order.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public OrderResponseDTO placeOrder(OrderRequestDTO orderRequestDTO) {
        return null;
    }

    @Override
    public List<OrderResponseDTO> fetchAllOrders() {
        return List.of();
    }

}
