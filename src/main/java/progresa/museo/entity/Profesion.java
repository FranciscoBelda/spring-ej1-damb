package progresa.museo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "profesion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profesion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "sueldo")
    private float sueldo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profesion")
    @JsonIgnore
    private List<Empleado> empleados;

}
