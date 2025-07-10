package org.generations.demo_security_spring.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class CustomSecurityConfig {
    private final CustomAuthenticationProvider customAuthenticationProvider;

    public CustomSecurityConfig(CustomAuthenticationProvider customAuthenticationProvider) {
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests -> {
                    authorizeRequests.requestMatchers("/api/v1/**").permitAll();
                    authorizeRequests.requestMatchers("/h2-console/**").permitAll();
                    authorizeRequests.anyRequest().authenticated();
                }).authenticationProvider(customAuthenticationProvider)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
