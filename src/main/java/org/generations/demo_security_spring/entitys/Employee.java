package org.generations.demo_security_spring.entitys;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "employee_roles",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Roles> roles;

/*
El tipo CascadeType.ALL va a realizar una propagación de todas las operaciones desde el padre a los hijos.
La anotación @ManyToMany se utiliza en ambas clases para crear la relación de muchos a muchos entre las entidades.

Esta asociación tiene dos lados, es decir, el lado propietario y el lado inverso.
En nuestro ejemplo, el lado propietario es Employee,
por lo que la tabla de unión se especifica en el lado propietario mediante la anotación @JoinTable
en la clase Employee.
@JoinTable se utiliza para definir la tabla de unión/vínculo. En este caso, es employee_roles.

La anotación @JoinColumn se utiliza para especificar la columna de unión/vinculación con la tabla principal.
Aquí, la columna de unión es empleado_id y role_id es la columna de unión inversa ya que Roles está en el lado inverso de la relación.

Una de las entidades debe propietaria de la relación y la otra opuesta o inversa
Los cambios se aplican a partir del lado propietario
El lado opuesto debe llevar el mappedBy
La elección del propietario y el lado inverso generalmente depende de cómo
se desea manipular y gestionar la relación en la aplicación
las operaciones de creación, actualización y eliminación de la relación Many-to-Many
se manejarán principalmente a través de la entidad Employee.
Esto implica que agregar o eliminar un Rol de un Employee será más fácil y más directo.

https://javaespanol.blogspot.com/2015/11/introduccion-jpa-parte-ii-las-relaciones.html
 */
}
