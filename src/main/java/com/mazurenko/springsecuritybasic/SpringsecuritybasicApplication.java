package com.mazurenko.springsecuritybasic;

import com.mazurenko.springsecuritybasic.filter.NameValidationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.www.BasicAuthenticationConverter;

@SpringBootApplication
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled=true)
public class SpringsecuritybasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringsecuritybasicApplication.class, args);
    }

    @Bean
    public BasicAuthenticationConverter basicAuthenticationConverter() {
        return new BasicAuthenticationConverter();
    }

    @Bean
    public NameValidationFilter nameValidationFilter() {
        return new NameValidationFilter(basicAuthenticationConverter());
    }
}
