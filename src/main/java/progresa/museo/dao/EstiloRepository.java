package progresa.museo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import progresa.museo.entity.Cuadro;
import progresa.museo.entity.Estilo;

import java.util.Optional;

@RepositoryRestResource
public interface EstiloRepository extends JpaRepository<Estilo, Long> {
    Optional<Estilo> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}
