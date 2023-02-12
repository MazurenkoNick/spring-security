package com.mazurenko.springsecuritybasic.service;


import com.mazurenko.springsecuritybasic.entity.Customer;
import com.mazurenko.springsecuritybasic.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SqlUserDetailsService implements UserDetailsService {

    CustomerRepository customerRepository;

    @Autowired
    public SqlUserDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // get user from database
        Customer customer = customerRepository.findByEmail(username);
        if (customer == null) {
            throw new UsernameNotFoundException("User " + username + " was not found");
        }
        // filling UserDetails (User) with authorities, password, username from the database
        String password = customer.getPassword();
        List<SimpleGrantedAuthority> authorities = customer.getAuthorities()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .toList();

        return new User(username, password, authorities);
    }
}
