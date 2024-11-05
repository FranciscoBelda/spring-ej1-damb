package progresa.museo.entity;
/*
* Estilo
* Empleado
* Dirección
* Profesión
* */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cuadro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cuadro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "pintor")
    private String pintor;

    @ManyToOne
    @JoinColumn(name = "estilo_id", nullable = false)
    private Estilo estilo;

    public Cuadro(String nombre, String pintor) {
        this.nombre = nombre;
        this.pintor = pintor;
    }
}
