package org.generations.demo_security_spring.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests -> {
                    authorizeRequests.requestMatchers("/api/v1/**").permitAll();
                    authorizeRequests.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("jose")
                .password(passwordEncoder().encode("jose"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("mario")
                .password(passwordEncoder().encode("mario"))
                .roles("ADMIN")
                .build();

        UserDetails admin1 = User.builder()
                .username("alba")
                .password(passwordEncoder().encode("alba"))
                .roles("ADMIN")
                .build();

        UserDetails user2 = User.builder()
                .username("patata")
                .password(passwordEncoder().encode("patata"))
                .roles("USER")
                .build();

        UserDetails user3 = User.builder()
                .username("superpatata")
                .password(passwordEncoder().encode("patata"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user, admin, admin1, user2, user3);
    }

}
