package com.mazurenko.springsecuritybasic.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.www.BasicAuthenticationConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NameValidationFilter implements Filter {

    BasicAuthenticationConverter authenticationConverter = new BasicAuthenticationConverter();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getHeader("Authorization") != null) {
            UsernamePasswordAuthenticationToken user = authenticationConverter.convert(request);
            if (user.getName().toLowerCase().trim().contains("test")) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                System.out.println("ERROR: USER'S NAME CAN'T CONTAIN 'test' DURING LOGIN");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
