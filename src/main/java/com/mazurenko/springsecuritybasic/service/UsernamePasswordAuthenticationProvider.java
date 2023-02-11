package com.mazurenko.springsecuritybasic.service;

import com.mazurenko.springsecuritybasic.entity.Authority;
import com.mazurenko.springsecuritybasic.entity.Customer;
import com.mazurenko.springsecuritybasic.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    CustomerRepository customerRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UsernamePasswordAuthenticationProvider(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        Customer user;

        // if user doesn't exist - throw an exception
        try {
            user = customerRepository.findByEmail(username);
        }
        catch (UsernameNotFoundException e) {
            throw new BadCredentialsException(e.getMessage());
        }

        String password = authentication.getCredentials().toString();
        // retrieve authorities of the user (customer) and convert them to GrantedAuthority list
        List<GrantedAuthority> grantedAuthorities = getGrantedAuthorities(user.getAuthorities());

        // if passwords match - return UsernamePasswordAuthenticationToken;
        if (passwordEncoder.matches(password, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(username, password, grantedAuthorities);
        }
        else throw new BadCredentialsException("Invalid password!");
    }

    // method that converts authorities from the database to the GrantedAuthority list
    private List<GrantedAuthority> getGrantedAuthorities(Collection<? extends Authority> authorities) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        authorities.stream()
                .map(Authority::getName)
                .map(SimpleGrantedAuthority::new)
                .forEach(grantedAuthorities::add);

        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
