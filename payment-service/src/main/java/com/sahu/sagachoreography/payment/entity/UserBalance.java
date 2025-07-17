package com.sahu.sagachoreography.payment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "saga_choreography_user_balance")
public class UserBalance {
    @Id
    private Long id;
    private BigDecimal balance;
}
