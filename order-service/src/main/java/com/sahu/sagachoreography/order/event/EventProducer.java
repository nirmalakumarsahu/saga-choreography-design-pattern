package com.sahu.sagachoreography.order.event;

import com.sahu.sagachoreography.common.dto.OrderResponseDTO;
import com.sahu.sagachoreography.common.event.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventProducer {

    private final Sinks.Many<OrderEvent> orderEventSink;

    public void produceOrderEvent(OrderResponseDTO orderResponseDTO) {
        log.info("Publishing OrderEvent for order: {}", orderResponseDTO.id());
        try {
            OrderEvent orderEvent = new OrderEvent(orderResponseDTO);

            // Publish the event to the sink
            orderEventSink.tryEmitNext(orderEvent)
                    .orThrow(); // This will throw an exception if the sink is full or closed

            log.info("OrderEvent published successfully for order: {}", orderResponseDTO.id());
        }
        catch (Exception e) {
            log.error("Failed to publish OrderEvent for order: {}. Error: {}", orderResponseDTO.id(), e.getMessage());
        }
    }

}
