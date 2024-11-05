package progresa.museo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progresa.museo.dao.DireccionRepository;
import progresa.museo.entity.Direccion;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DireccionService {
    @Autowired
    private DireccionRepository direccionRepository;

    public List<Direccion> list(){
        return direccionRepository.findAll();
    }

    public Optional<Direccion> getById(long id){
        return direccionRepository.findById(id);
    }
    public Optional<Direccion> getByCalle(String calle){
        return direccionRepository.findByCalle(calle);
    }
    public  void save(Direccion cuadro){
        direccionRepository.save(cuadro);
    }
    public void delete(long id){
        direccionRepository.deleteById(id);
    }
    public boolean existsById(long id){
        return direccionRepository.existsById(id);
    }
    public boolean existsByCalle(String calle){
        return direccionRepository.existsByCalle(calle);
    }
}
