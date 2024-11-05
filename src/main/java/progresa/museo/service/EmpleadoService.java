package progresa.museo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progresa.museo.dao.EmpleadoRepository;
import progresa.museo.entity.Empleado;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> list(){
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> getById(long id){
        return empleadoRepository.findById(id);
    }
    public Optional<Empleado> getByNombre(String nombre){
        return empleadoRepository.findByNombre(nombre);
    }
    public  void save(Empleado cuadro){
        empleadoRepository.save(cuadro);
    }
    public void delete(long id){
        empleadoRepository.deleteById(id);
    }
    public boolean existsById(long id){
        return empleadoRepository.existsById(id);
    }
    public boolean existsByNombre(String nombre){
        return empleadoRepository.existsByNombre(nombre);
    }
}
