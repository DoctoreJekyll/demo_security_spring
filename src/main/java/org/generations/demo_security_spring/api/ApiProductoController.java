package org.generations.demo_security_spring.api;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.generations.demo_security_spring.entitys.Producto;
import org.generations.demo_security_spring.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/producto")
public class ApiProductoController {

    ProductoService productoService;

    public ApiProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> findById(@PathVariable("id") long id) {
        Optional<Producto> productoOptional = productoService.findById(id);
        if (productoOptional.isPresent()) {
            return new ResponseEntity<>(productoOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Producto>> findAll() {
        return new ResponseEntity<>(productoService.findAll(), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Producto> create(@RequestBody Producto producto) {
        productoService.save(producto);
        return new ResponseEntity<>(producto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Producto> update(@RequestBody Producto producto) {
        Optional<Producto> productoOptional = productoService.findById(producto.getId());
        if (productoOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        productoService.save(producto);  // ← guardas el objeto actualizado
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Producto> delete(@RequestBody Producto producto) {
        Optional<Producto> productoOptional = productoService.findById(producto.getId());
        if (productoOptional.isPresent()) {
            productoService.deleteById(producto.getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
