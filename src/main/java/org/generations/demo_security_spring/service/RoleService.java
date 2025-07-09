package org.generations.demo_security_spring.service;

import org.generations.demo_security_spring.entitys.Roles;
import org.generations.demo_security_spring.repository.RolesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService{

    private RolesRepository rolesRepository;

    public RoleService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public List<Roles> getRoles(){
        return rolesRepository.findAll();
    }

    public Optional<Roles> getRoleById(Long id){
        return rolesRepository.findById(id);
    }

    public void save(Roles roles) {
        rolesRepository.save(roles);
    }

    public void deletebyId(Long id) {
        rolesRepository.deleteById(id);
    }
}
