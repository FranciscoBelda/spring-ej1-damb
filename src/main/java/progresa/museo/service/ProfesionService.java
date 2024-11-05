package progresa.museo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progresa.museo.dao.ProfesionRepository;
import progresa.museo.entity.Profesion;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProfesionService {
    @Autowired
    private ProfesionRepository profesionRepository;

    public List<Profesion> list(){
        return profesionRepository.findAll();
    }

    public Optional<Profesion> getById(long id){
        return profesionRepository.findById(id);
    }
    public Optional<Profesion> getByNombre(String nombre){
        return profesionRepository.findByNombre(nombre);
    }
    public  void save(Profesion cuadro){
        profesionRepository.save(cuadro);
    }
    public void delete(long id){
        profesionRepository.deleteById(id);
    }
    public boolean existsById(long id){
        return profesionRepository.existsById(id);
    }
    public boolean existsByNombre(String nombre){
        return profesionRepository.existsByNombre(nombre);
    }
}
