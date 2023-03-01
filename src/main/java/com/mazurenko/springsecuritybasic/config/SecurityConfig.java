package com.mazurenko.springsecuritybasic.config;

import com.mazurenko.springsecuritybasic.filter.CsrfCookieFilter;
import com.mazurenko.springsecuritybasic.filter.JwtTokenGeneratorFilter;
import com.mazurenko.springsecuritybasic.filter.JwtTokenValidationFilter;
import com.mazurenko.springsecuritybasic.filter.NameValidationFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;
import java.util.List;


@Configuration
public class SecurityConfig {

    CsrfCookieFilter csrfCookieFilter;
    JwtTokenValidationFilter jwtTokenValidationFilter;
    JwtTokenGeneratorFilter jwtTokenGeneratorFilter;
    NameValidationFilter nameValidationFilter;

    @Autowired
    public SecurityConfig(CsrfCookieFilter csrfCookieFilter, JwtTokenValidationFilter jwtTokenValidationFilter,
                          JwtTokenGeneratorFilter jwtTokenGeneratorFilter, NameValidationFilter nameValidationFilter) {
        this.csrfCookieFilter = csrfCookieFilter;
        this.jwtTokenValidationFilter = jwtTokenValidationFilter;
        this.jwtTokenGeneratorFilter = jwtTokenGeneratorFilter;
        this.nameValidationFilter = nameValidationFilter;
    }

    // Adding custom security requirements
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        CsrfTokenRequestAttributeHandler csrfRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();

        /**
         *  From Spring Security 6, The Authentication details will not be saved automatically into SecurityContextHolder. To change this behaviour either we
         *  need to save these details explicitly into SecurityContextHolder or we can configure securityContext().requireExplicitSave(false) like shown below.
         *  We set SessionCreationPolicy.STATELESS to sessionCreationPolicy, which means never create an HttpSession. We'll take care of creating our own SessionManagement
         */
        http.securityContext().requireExplicitSave(false)
            .and()
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .cors().configurationSource(new CorsConfigurationSource() {
                @Override
                public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setMaxAge(3600L);
                    config.setExposedHeaders(List.of("Authorization")); // allows set the list of response headers other than simple headers
                    return config;
                }
                /**
                 *  In Spring Security 5, the default behavior is that the CsrfToken will be loaded on every request. Whereas with
                 *  Spring Security 6, the default is that the lookup of the CsrfToken will be deferred until it is needed. The developer
                 *  has to write logic to read the CSRF token and send it as part of the response. When framework sees the CSRF token
                 *  in the response header, it takes care of sending the same as Cookie as well. For the same, we need to use CsrfTokenRequestAttributeHandler
                 *  and create a filter with the name CsrfCookieFilter which runs every time after the Spring Security in built filter BasicAuthenticationFilter
                 *  like shown below. More details about Filters, are discussed inside the Section 8 of the course.
                 */
            })
            .and()
            .csrf().disable() // while using jwt token
//            .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer
//                    .csrfTokenRequestHandler(csrfRequestAttributeHandler)
//                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                    .ignoringRequestMatchers("/register")
//                )
            .addFilterBefore(nameValidationFilter, BasicAuthenticationFilter.class)
            .addFilterBefore(jwtTokenValidationFilter, BasicAuthenticationFilter.class)
            .addFilterAfter(jwtTokenGeneratorFilter, BasicAuthenticationFilter.class)
            .authorizeHttpRequests()
                .requestMatchers("/notices","/contact","/register").permitAll()
                .requestMatchers("/user").authenticated()
                .requestMatchers("/myAccount").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/myLoans/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/myCards").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/myBalance").hasAnyRole("USER", "ADMIN")
            .and()
            .formLogin()
            .and()
            .httpBasic();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
