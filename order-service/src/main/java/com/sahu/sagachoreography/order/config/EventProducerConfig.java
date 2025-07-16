package com.sahu.sagachoreography.order.config;

import com.sahu.sagachoreography.common.event.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Slf4j
@Configuration
public class EventProducerConfig {

    @Bean
    public Sinks.Many<OrderEvent> orderEventSink() {
        log.info("Sink for OrderEvent created");
        return Sinks.many().multicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<OrderEvent>> orderEventProducer(Sinks.Many<OrderEvent> orderEventSink) {
        log.info("OrderEvent pushed to the topic");
        return orderEventSink::asFlux;
    }

}
