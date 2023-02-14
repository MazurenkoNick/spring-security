package com.mazurenko.springsecuritybasic.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationConverter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class NameValidationFilter extends OncePerRequestFilter {

    BasicAuthenticationConverter authenticationConverter;

    public NameValidationFilter() {
    }

    public NameValidationFilter(BasicAuthenticationConverter authenticationConverter) {
        this.authenticationConverter = authenticationConverter;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("auth in security context:"+SecurityContextHolder.getContext().getAuthentication());
        if (request.getHeader("Authorization") != null) {
            UsernamePasswordAuthenticationToken user = authenticationConverter.convert(request);
            if (user.getName().toLowerCase().trim().contains(".ru")) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                System.out.println("ERROR: USER'S NAME CAN'T CONTAIN '.ru' DURING LOGIN");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/user");
    }
}
