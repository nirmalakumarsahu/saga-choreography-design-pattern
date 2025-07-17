package com.sahu.sagachoreography.payment.service.impl;

import com.sahu.sagachoreography.common.constant.PaymentStatus;
import com.sahu.sagachoreography.common.dto.OrderRequestDTO;
import com.sahu.sagachoreography.common.dto.OrderResponseDTO;
import com.sahu.sagachoreography.common.dto.PaymentResponseDTO;
import com.sahu.sagachoreography.common.event.OrderEvent;
import com.sahu.sagachoreography.common.event.PaymentEvent;
import com.sahu.sagachoreography.payment.entity.UserBalance;
import com.sahu.sagachoreography.payment.entity.UserTransaction;
import com.sahu.sagachoreography.payment.repository.UserBalanceRepository;
import com.sahu.sagachoreography.payment.repository.UserTransactionRepository;
import com.sahu.sagachoreography.payment.service.PaymentService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final UserBalanceRepository userBalanceRepository;
    private final UserTransactionRepository userTransactionRepository;

    @PostConstruct
    public void initializeUserBalanceInDB() {
        log.info("Initializing user balance in the database");
        userBalanceRepository.saveAll(Stream.of(
                new UserBalance(101L, new BigDecimal("1000.00")),
                new UserBalance(102L, new BigDecimal("2000.00")),
                new UserBalance(103L, new BigDecimal("3000.00")),
                new UserBalance(104L, new BigDecimal("4000.00")),
                new UserBalance(105L, new BigDecimal("5000.00"))
        ).toList());
    }

    @Transactional
    @Override
    public PaymentEvent processPaymentAndPlaceOrder(OrderEvent orderEvent) {
        log.info("Processing payment for order: {}", orderEvent.getUuid());
        OrderResponseDTO orderResponseDTO = orderEvent.getOrderResponseDTO();
        log.info("Order details: {}", orderResponseDTO);

        // Simulate payment processing logic
        return userBalanceRepository.findById(orderResponseDTO.userId())
                //Based on the userId in the orderResponseDTO, find the user balance and check if the user has sufficient balance
                .filter(userBalance -> userBalance.getBalance().compareTo( orderResponseDTO.price())>0)
                .map( userBalance -> {
                    log.info("User balance before payment: {}", userBalance.getBalance());
                    // Deduct the order price from the user's balance
                    userBalance.setBalance(userBalance.getBalance().subtract(orderResponseDTO.price()));
                    // Save the updated user balance
                    UserTransaction userTransaction = userTransactionRepository.save(
                            UserTransaction.builder()
                                    .orderId(orderResponseDTO.id())
                                    .userId(orderResponseDTO.userId())
                                    .amount(orderResponseDTO.price())
                                    .build()
                    );

                    return createPaymentEvent(orderResponseDTO, userTransaction);
                }).orElse(createPaymentEvent(orderResponseDTO, null));
    }

    private PaymentEvent createPaymentEvent(OrderResponseDTO orderResponseDTO, UserTransaction userTransaction) {
        return  new PaymentEvent(PaymentResponseDTO.builder()
                .id(Objects.nonNull(userTransaction) ? userTransaction.getId() : null)
                .orderId(orderResponseDTO.id())
                .userId(orderResponseDTO.userId())
                .amount(orderResponseDTO.price())
                .paymentStatus(Objects.nonNull(userTransaction) ? PaymentStatus.PAYMENT_COMPLETED : PaymentStatus.PAYMENT_FAILED)
                .build());
    }

    @Transactional
    @Override
    public void cancelOrderAndRefundPayment(OrderEvent orderEvent) {
        OrderResponseDTO orderResponseDTO = orderEvent.getOrderResponseDTO();
        userTransactionRepository.findByOrderId(orderResponseDTO.id())
                        .ifPresent(userTransaction -> {
                            userTransactionRepository.delete(userTransaction);

                            userBalanceRepository.findById(userTransaction.getUserId())
                                    .ifPresent(userBalance -> {
                                        userBalance.setBalance(userBalance.getBalance().add(userTransaction.getAmount()));
                                    });
                        });
    }

}
