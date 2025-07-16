package com.sahu.sagachoreography.order.service.impl;

import com.sahu.sagachoreography.common.dto.OrderRequestDTO;
import com.sahu.sagachoreography.common.dto.OrderResponseDTO;
import com.sahu.sagachoreography.order.entity.Order;
import com.sahu.sagachoreography.order.event.EventProducer;
import com.sahu.sagachoreography.order.repository.OrderRepository;
import com.sahu.sagachoreography.order.service.OrderService;
import com.sahu.sagachoreography.order.service.util.OrderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final EventProducer eventProducer;

    @Override
    public OrderResponseDTO placeOrder(OrderRequestDTO orderRequestDTO) {
        Order order = OrderUtil.toOrder(orderRequestDTO);
        order = orderRepository.save(order);

        OrderResponseDTO orderResponseDTO = OrderUtil.orderResponseDTO(order);

        // Here you would typically publish an event to a message broker
        eventProducer.produceOrderEvent(orderResponseDTO);

        return orderResponseDTO;
    }

    @Override
    public List<OrderResponseDTO> fetchAllOrders() {
        return OrderUtil.orderResponseDTOS(orderRepository.findAll());
    }

}
