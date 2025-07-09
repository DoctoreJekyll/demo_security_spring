package org.generations.demo_security_spring.repository;

import org.generations.demo_security_spring.entitys.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
