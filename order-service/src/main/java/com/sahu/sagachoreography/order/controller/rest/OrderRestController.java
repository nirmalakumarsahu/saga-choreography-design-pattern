package com.sahu.sagachoreography.order.controller.rest;

import com.sahu.sagachoreography.common.dto.OrderRequestDTO;
import com.sahu.sagachoreography.common.dto.OrderResponseDTO;
import com.sahu.sagachoreography.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<List<OrderResponseDTO>> fetchAllOrders() {
        return ResponseEntity.ok(orderService.fetchAllOrders());
    }

    @PostMapping("/place")
    public ResponseEntity<OrderResponseDTO> placeOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.placeOrder(orderRequestDTO));
    }

}
