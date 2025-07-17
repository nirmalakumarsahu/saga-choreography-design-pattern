package com.sahu.sagachoreography.payment.repository;

import com.sahu.sagachoreography.payment.entity.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBalanceRepository extends JpaRepository<UserBalance, Long> {
}
