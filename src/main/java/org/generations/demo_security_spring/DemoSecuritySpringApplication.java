package org.generations.demo_security_spring;

import org.generations.demo_security_spring.entitys.Producto;
import org.generations.demo_security_spring.repository.ProductoRepository;
import org.generations.demo_security_spring.service.ProductoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoSecuritySpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSecuritySpringApplication.class, args);
    }

}
