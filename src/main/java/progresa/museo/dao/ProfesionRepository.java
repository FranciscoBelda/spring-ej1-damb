package progresa.museo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import progresa.museo.entity.Cuadro;
import progresa.museo.entity.Profesion;

import java.util.Optional;

@RepositoryRestResource
public interface ProfesionRepository extends JpaRepository<Profesion, Long> {
    Optional<Profesion> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}
