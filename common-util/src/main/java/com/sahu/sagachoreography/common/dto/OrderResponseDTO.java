package com.sahu.sagachoreography.common.dto;

import com.sahu.sagachoreography.common.constant.OrderStatus;
import lombok.Builder;

@Builder
public record OrderResponseDTO(
        Long id,
        Long productId,
        Long userId,
        Integer quantity,
        Double price,
        OrderStatus orderStatus
){

}
