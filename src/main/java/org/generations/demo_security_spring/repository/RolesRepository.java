package org.generations.demo_security_spring.repository;


import org.generations.demo_security_spring.entitys.Roles;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RolesRepository extends JpaRepository<Roles,Long> {

}
