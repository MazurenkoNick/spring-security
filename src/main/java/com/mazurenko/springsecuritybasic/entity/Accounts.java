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

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="account_number")
    private Long accountNumber;

    @Column(name="account_type")
    private String accountType;

    @Column(name="branch_address")
    private String branchAddress;

    @Column(name="create_dt")
    private Date createDt;
}
