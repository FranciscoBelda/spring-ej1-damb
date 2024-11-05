package progresa.museo.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import progresa.museo.dto.EmpleadoDto;
import progresa.museo.dto.Mensaje;
import progresa.museo.entity.Direccion;
import progresa.museo.entity.Empleado;
import progresa.museo.entity.Profesion;
import progresa.museo.service.EmpleadoService;
import progresa.museo.service.ProfesionService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/museo/empleado")
@CrossOrigin(origins = "*")
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    ProfesionService profesionService;

    @GetMapping("/listado")
    public ResponseEntity<List<Empleado>> list(){
        List<Empleado> empleados = empleadoService.list();
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") long id){
        if(!empleadoService.existsById(id))
            return new ResponseEntity<>(
                    new Mensaje("No existe un empleado con ese id."),
                    HttpStatus.NOT_FOUND);
        if(empleadoService.getById(id).isPresent()){
            Empleado empleado = empleadoService.getById(id).get();
            return new ResponseEntity<>(empleado, HttpStatus.OK);
        }
        return new ResponseEntity<>(
                new Mensaje("No existe un empleado con ese id."),
                HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscarPorNombre/{nombre}")
    public ResponseEntity<?> getByNombre(@PathVariable("nombre") String nombre){
        if(!empleadoService.existsByNombre(nombre))
            return new ResponseEntity<>(
                    new Mensaje("No existe un empleado con ese nombre."),
                    HttpStatus.NOT_FOUND);
        if(empleadoService.getByNombre(nombre).isPresent()){
            Empleado empleado = empleadoService.getByNombre(nombre).get();
            return new ResponseEntity<>(empleado, HttpStatus.OK);
        }
        return new ResponseEntity<>(
                new Mensaje("No existe un empleado con ese nombre."),
                HttpStatus.NOT_FOUND);
    }
    
    @PostMapping("/crear")
    public ResponseEntity<?> create(
            @RequestBody EmpleadoDto empleadoDto){
        if(StringUtils.isBlank(empleadoDto.getNombre()))
           return new ResponseEntity<>(
                    new Mensaje("El nombre del empleado es obligatorio."),
                    HttpStatus.BAD_REQUEST);
        
        if (empleadoService.existsByNombre(empleadoDto.getNombre()))
            return new ResponseEntity<>(
                    new Mensaje("El nombre del empleado ya existe."),
                    HttpStatus.BAD_REQUEST);
        
        Empleado empleado = new Empleado();
        empleado.setNombre(empleadoDto.getNombre());
        empleado.setEdad(empleadoDto.getEdad());

        Direccion direccion = new Direccion();
        if (StringUtils.isBlank(empleadoDto.getDireccion().getCalle()))
            return new ResponseEntity<>(
                    new Mensaje("La calle de la dirección del empleado es obligatoria"),
                    HttpStatus.BAD_REQUEST);

        direccion.setCalle(empleadoDto.getDireccion().getCalle());
        direccion.setCiudad(empleadoDto.getDireccion().getCiudad());

        direccion.setEmpleado(empleado);
        empleado.setDireccion(direccion);

        Profesion profesion;
        if(profesionService.getByNombre(
                    empleadoDto.getProfesion().getNombre()).isPresent()){
            profesion = profesionService.getByNombre(
                    empleadoDto.getProfesion().getNombre()).get();
        }else {
            if(StringUtils.isBlank(empleadoDto.getProfesion().getNombre()))
                return new ResponseEntity<>(
                        new Mensaje("El nombre del profesion es obligatorio."),
                        HttpStatus.BAD_REQUEST);
            profesion = new Profesion();
            profesion.setNombre(empleadoDto.getProfesion().getNombre());
            profesion.setEmpleados(new ArrayList<>());
        }
        profesion.getEmpleados().add(empleado);
        empleado.setProfesion(profesion);
        
        profesionService.save(profesion);
        return new ResponseEntity<>(
                new Mensaje("Empleado creado"),
                HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(
            @RequestBody EmpleadoDto empleadoDto,
            @PathVariable("id") long id){
        
        if (!empleadoService.existsById(id))
            return new ResponseEntity<>(
                    new Mensaje("No existe un empleado con ese id."),
                    HttpStatus.NOT_FOUND);
        
        if (empleadoService.existsByNombre(
                empleadoDto.getNombre()) && 
        empleadoService.getById(id).get().getId() != id){
            return new ResponseEntity<>(
                    new Mensaje("Ya existe un empleado con ese nombre."),
                    HttpStatus.BAD_REQUEST);
        }

        if(StringUtils.isBlank(empleadoDto.getNombre()))
            return new ResponseEntity<>(
                    new Mensaje("El nombre del empleado es obligatorio."),
                    HttpStatus.BAD_REQUEST);
        
        if(empleadoService.getById(id).isPresent()) {
            Empleado empleado = empleadoService.getById(id).get();
            empleado.setNombre(empleadoDto.getNombre());
            empleado.setEdad(empleadoDto.getEdad());


            Direccion direccion = new Direccion();
            if (StringUtils.isBlank(empleadoDto.getDireccion().getCalle()))
                return new ResponseEntity<>(
                        new Mensaje("La calle de la dirección del empleado es obligatoria"),
                        HttpStatus.BAD_REQUEST);

            direccion.setCalle(empleadoDto.getDireccion().getCalle());
            direccion.setCiudad(empleadoDto.getDireccion().getCiudad());

            direccion.setEmpleado(empleado);
            empleado.setDireccion(direccion);

            Profesion profesion;
            if(profesionService.getByNombre(
                    empleadoDto.getProfesion().getNombre()).isPresent()){
                profesion = profesionService.getByNombre(
                        empleadoDto.getProfesion().getNombre()).get();
            }else {
                if(StringUtils.isBlank(empleadoDto.getProfesion().getNombre()))
                    return new ResponseEntity<>(
                            new Mensaje("El nombre del profesion es obligatorio."),
                            HttpStatus.BAD_REQUEST);
                profesion = new Profesion();
                profesion.setNombre(empleadoDto.getProfesion().getNombre());
                profesion.setEmpleados(new ArrayList<>());
            }
            profesion.getEmpleados().add(empleado);
            empleado.setProfesion(profesion);

            profesionService.save(profesion);
            return new ResponseEntity<>(
                    new Mensaje("Empleado actualizado"),
                    HttpStatus.OK);


        }

        return new ResponseEntity<>(
                new Mensaje("El id no existe"),
                HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        if (!empleadoService.existsById(id))
            return new ResponseEntity<>(
                    new Mensaje("No existe un empleado con ese id"),
                    HttpStatus.NOT_FOUND);
        empleadoService.delete(id);
        return new ResponseEntity<>(
                new Mensaje("Empleado borrado"),
                HttpStatus.OK);
    }

}
