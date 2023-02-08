package com.mazurenko.springsecuritybasic.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="cards")
public class Cards {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="card_id")
    private Long cardId;

    @Column(name="card_number")
    private String cardNumber;

    @Column(name="customer_id")
    private Long customerId;

    @Column(name="card_type")
    private String cardType;

    @Column(name="total_limit")
    private Long totalLimit;

    @Column(name="amount_used")
    private Long amountUsed;

    @Column(name="available_amount")
    private Long availableAmount;

    @Column(name="create_dt")
    private Date createDt;
}
