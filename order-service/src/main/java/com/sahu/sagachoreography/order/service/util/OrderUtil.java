package com.sahu.sagachoreography.order.service.util;

import com.sahu.sagachoreography.common.constant.OrderStatus;
import com.sahu.sagachoreography.common.dto.OrderRequestDTO;
import com.sahu.sagachoreography.common.dto.OrderResponseDTO;
import com.sahu.sagachoreography.order.entity.Order;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class OrderUtil {

    public Order toOrder(OrderRequestDTO orderRequestDTO) {
        return Order.builder()
                .price(orderRequestDTO.price())
                .quantity(orderRequestDTO.quantity())
                .userId(orderRequestDTO.userId())
                .productId(orderRequestDTO.productId())
                .orderStatus(OrderStatus.ORDER_CREATED)
                .build();
    }

    public OrderResponseDTO orderResponseDTO(Order order) {
        return  OrderResponseDTO.builder()
                .id(order.getId())
                .orderStatus(order.getOrderStatus())
                .price(order.getPrice())
                .quantity(order.getQuantity())
                .userId(order.getUserId())
                .productId(order.getProductId())
                .build();
    }

    public List<OrderResponseDTO> orderResponseDTOS(List<Order> orders) {
        return orders.stream().map(OrderUtil::orderResponseDTO).toList();
    }

}
