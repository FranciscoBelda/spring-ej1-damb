package progresa.museo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progresa.museo.dao.CuadroRepository;
import progresa.museo.entity.Cuadro;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CuadroService {
    @Autowired
    private CuadroRepository cuadroRepository;

    public List<Cuadro> list(){
        return cuadroRepository.findAll();
    }

    public Optional<Cuadro> getById(long id){
        return cuadroRepository.findById(id);
    }
    public Optional<Cuadro> getByNombre(String nombre){
        return cuadroRepository.findByNombre(nombre);
    }
    public  void save(Cuadro cuadro){
        cuadroRepository.save(cuadro);
    }
    public void delete(long id){
        cuadroRepository.deleteById(id);
    }
    public boolean existsById(long id){
        return cuadroRepository.existsById(id);
    }
    public boolean existsByNombre(String nombre){
        return cuadroRepository.existsByNombre(nombre);
    }
}
