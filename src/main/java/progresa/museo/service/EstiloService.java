package progresa.museo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progresa.museo.dao.EstiloRepository;
import progresa.museo.entity.Estilo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EstiloService {
    @Autowired
    private EstiloRepository estiloRepository;

    public List<Estilo> list(){
        return estiloRepository.findAll();
    }

    public Optional<Estilo> getById(long id){
        return estiloRepository.findById(id);
    }
    public Optional<Estilo> getByNombre(String nombre){
        return estiloRepository.findByNombre(nombre);
    }
    public  void save(Estilo cuadro){
        estiloRepository.save(cuadro);
    }
    public void delete(long id){
        estiloRepository.deleteById(id);
    }
    public boolean existsById(long id){
        return estiloRepository.existsById(id);
    }
    public boolean existsByNombre(String nombre){
        return estiloRepository.existsByNombre(nombre);
    }
}
