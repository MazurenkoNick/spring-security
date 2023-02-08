package com.mazurenko.springsecuritybasic.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="loans")
public class Loans {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="loan_number")
    private Long loanNumber;

    @Column(name="customer_id")
    private Long customerId;

    @Column(name="start_dt")
    private Date startDt;

    @Column(name="loan_type")
    private String loanType;

    @Column(name="total_loan")
    private Long totalLoan;

    @Column(name="amount_paid")
    private Long amountPaid;

    @Column(name="outstanding_amount")
    private Long outstandingAmount;

    @Column(name="create_dt")
    private Date createDt;

}
