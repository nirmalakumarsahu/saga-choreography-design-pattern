package com.sahu.sagachoreography.common.dto;

import lombok.Builder;

@Builder
public record OrderRequestDTO(
    Long productId,
    Long userId,
    Integer quantity,
    Double price
){

}
