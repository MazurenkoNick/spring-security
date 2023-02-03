package com.mazurenko.springsecuritybasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class SpringsecuritybasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringsecuritybasicApplication.class, args);
    }
}
