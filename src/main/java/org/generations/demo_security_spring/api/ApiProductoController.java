package org.generations.demo_security_spring.api;


import lombok.extern.slf4j.Slf4j;
import org.generations.demo_security_spring.entitys.Employee;
import org.generations.demo_security_spring.entitys.Producto;
import org.generations.demo_security_spring.service.EmployeeService;
import org.generations.demo_security_spring.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/producto")
public class ApiProductoController {

    ProductoService productoService;
    EmployeeService employeeService;

    public ApiProductoController(ProductoService productoService, EmployeeService employeeService) {
        this.productoService = productoService;
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or isAnonymous()")
    public ResponseEntity<Producto> findById(@PathVariable("id") long id) {
        Optional<Producto> productoOptional = productoService.findById(id);
        if (productoOptional.isPresent()) {
            return new ResponseEntity<>(productoOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        Employee employee = employeeService.getEmployee(id).get();
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or isAnonymous()")
    @GetMapping
    public ResponseEntity<List<Producto>> findAll() {
        return new ResponseEntity<>(productoService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Producto> create(@RequestBody Producto producto) {
        productoService.save(producto);
        return new ResponseEntity<>(producto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<Producto> update(@RequestBody Producto producto) {
        Optional<Producto> productoOptional = productoService.findById(producto.getId());
        if (productoOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        productoService.save(producto);  // ← guardas el objeto actualizado
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> delete(@PathVariable("id") long id) {
        Optional<Producto> productoOptional = productoService.findById(id);
        if (productoOptional.isPresent()) {
            productoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
