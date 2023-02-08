package com.mazurenko.springsecuritybasic.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="accounts")
public class Accounts {

    @Column(name="customer_id")
    private Long customerId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="account_number")
    private Long accountNumber;

    @Column(name="account_type")
    private String accountType;

    @Column(name="branch_address")
    private Long branchAddress;

    @Column(name="create_dt")
    private Date createDt;
}
