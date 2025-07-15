package com.sahu.sagachoreography.common.event;

import com.sahu.sagachoreography.common.dto.OrderResponseDTO;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class OrderEvent {
    private UUID uuid = UUID.randomUUID();
    private Date date = new Date();
    @NonNull
    private OrderResponseDTO orderResponseDTO;
}
