package progresa.museo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import progresa.museo.entity.Cuadro;

import java.util.Optional;

@RepositoryRestResource
public interface CuadroRepository extends JpaRepository<Cuadro, Long> {
    Optional<Cuadro> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}
