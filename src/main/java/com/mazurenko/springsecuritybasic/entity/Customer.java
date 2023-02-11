package com.mazurenko.springsecuritybasic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy="customer", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Authority> authorities;

    @Column(name="name")
    private String name;

    @Email
    @NotNull
    private String email;

    // password hash shouldn't be sent to the ui. But we want it in
    // the request from the ui to the backend.
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
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
