package com.mazurenko.springsecuritybasic.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long id;

    @Column(name="name")
    private String name;
 
    @Email
    @NotNull
    private String email;

    @NotNull(message="Password can't be null")
    @Column(name="pwd")
    private String password;

    @NotNull(message="Role can't be null")
    private String role;

    @Column(name="mobile_number")
    private String mobileNumber;

    @Column(name="create_dt")
    private Date createDt;
}
