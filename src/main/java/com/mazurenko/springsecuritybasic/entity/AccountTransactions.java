package com.mazurenko.springsecuritybasic.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="account_transactions")
public class AccountTransactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="transaction_id")
    private String transactionId;

    @Column(name="account_number")
    private Long accountNumber;

    @Column(name="customer_id")
    private Long customerId;

    @Column(name="transaction_dt")
    private Date transactionDt;

    @Column(name="transaction_summary")
    private String transactionSummary;

    @Column(name="transaction_type")
    private String transactionType;

    @Column(name="transaction_amt")
    private Long transactionAmt;

    @Column(name="closing_balance")
    private Long closingBalance;

    @Column(name="create_dt")
    private Date createDt;
}
