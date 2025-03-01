package com.medilabo.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/eureka/**").permitAll() // Allow Eureka server access without authentication
                        .pathMatchers("/login").permitAll() // Allow Eureka server access without authentication
                        .pathMatchers("/").authenticated() // Allow home route without authentication
                        .pathMatchers("/backend/patients/**").authenticated() // Require authentication for patient frontend routes
                        .pathMatchers("/patients/record/update/**").authenticated() // Require authentication for patient service routes
                        .pathMatchers("/patients/risk/**").authenticated() // Require authentication for risk assessment routes
                        .pathMatchers("/patients/update/**").authenticated() // Require authentication for risk assessment routes
                        .pathMatchers("/patients/delete/**").authenticated() // Require authentication for risk assessment routes
                        .anyExchange().authenticated()
                )
                .formLogin(Customizer.withDefaults()) // Use default login form
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("admin")
                .password(passwordEncoder.encode("password"))
                .roles("ADMIN")
                .build();
        return new MapReactiveUserDetailsService(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}




