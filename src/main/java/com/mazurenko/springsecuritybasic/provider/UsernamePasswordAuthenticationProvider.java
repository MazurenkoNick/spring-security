package com.mazurenko.springsecuritybasic.provider;

import com.mazurenko.springsecuritybasic.service.SqlUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    SqlUserDetailsService userDetailsService;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UsernamePasswordAuthenticationProvider(SqlUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        UserDetails user;

        // if user doesn't exist - throw an exception
        try {
            user = userDetailsService.loadUserByUsername(username);
        }
        catch (UsernameNotFoundException e) {
            throw new BadCredentialsException(e.getMessage());
        }

        String password = authentication.getCredentials().toString();

        // if passwords match - return UsernamePasswordAuthenticationToken;
        if (passwordEncoder.matches(password, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
        }
        else throw new BadCredentialsException("Invalid password!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
