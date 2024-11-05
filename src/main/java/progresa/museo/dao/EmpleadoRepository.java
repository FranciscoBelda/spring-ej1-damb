package progresa.museo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import progresa.museo.entity.Cuadro;
import progresa.museo.entity.Empleado;

import java.util.Optional;

@RepositoryRestResource
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}
