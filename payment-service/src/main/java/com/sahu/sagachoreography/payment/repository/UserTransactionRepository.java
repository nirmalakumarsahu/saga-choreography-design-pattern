package com.sahu.sagachoreography.payment.repository;

import com.sahu.sagachoreography.payment.entity.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTransactionRepository extends JpaRepository<UserTransaction, Long> {

    Optional<UserTransaction> findByOrderId(Long orderId);

}
