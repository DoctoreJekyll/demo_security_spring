package org.generations.demo_security_spring.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;


public class CustomAuthenticationProvider implements AuthenticationProvider {

    private ExternalAuthService externalAuthService;
    int times = 0;

    public CustomAuthenticationProvider(ExternalAuthService externalAuthService) {
        this.externalAuthService = externalAuthService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        boolean success = externalAuthService.authenticate(username, password);

        if (success)
        {
            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            return new UsernamePasswordAuthenticationToken(username, password, authorities);
        }
        else
        {
            times++;
            double random = externalAuthService.randomNumber();
            System.out.println("Authentication failed " +  times + " tu umero es " +  random);
            throw new BadCredentialsException("Mala suerte");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
