package com.mazurenko.springsecuritybasic.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.www.BasicAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class NameValidationFilter extends OncePerRequestFilter {

    BasicAuthenticationConverter authenticationConverter = new BasicAuthenticationConverter();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

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
}
