package org.generations.demo_security_spring.config;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ExternalAuthService {

    private final Random random = new Random();

    public boolean authenticate(String username, String password) {
        return randomNumber() < 0.4;
    }

    public double randomNumber() {
        return random.nextDouble();
    }
}
