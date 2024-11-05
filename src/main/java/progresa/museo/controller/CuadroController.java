package progresa.museo.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import progresa.museo.dto.CuadroDto;
import progresa.museo.dto.Mensaje;
import progresa.museo.entity.Cuadro;
import progresa.museo.entity.Estilo;
import progresa.museo.service.CuadroService;
import progresa.museo.service.EstiloService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/museo/cuadro")
@CrossOrigin(origins = "*")
public class CuadroController {
    @Autowired
    CuadroService cuadroService;

    @Autowired
    EstiloService estiloService;

    @GetMapping("/listado")
    public ResponseEntity<List<Cuadro>> list(){
        List<Cuadro> cuadros = cuadroService.list();
        return new ResponseEntity<>(cuadros, HttpStatus.OK);
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") long id){
        if(!cuadroService.existsById(id))
            return new ResponseEntity<>(
                    new Mensaje("No existe un cuadro con ese id."),
                    HttpStatus.NOT_FOUND);
        if(cuadroService.getById(id).isPresent()){
            Cuadro cuadro = cuadroService.getById(id).get();
            return new ResponseEntity<>(cuadro, HttpStatus.OK);
        }
        return new ResponseEntity<>(
                new Mensaje("No existe un cuadro con ese id."),
                HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscarPorNombre/{nombre}")
    public ResponseEntity<?> getByNombre(@PathVariable("nombre") String nombre){
        if(!cuadroService.existsByNombre(nombre))
            return new ResponseEntity<>(
                    new Mensaje("No existe un cuadro con ese nombre."),
                    HttpStatus.NOT_FOUND);
        if(cuadroService.getByNombre(nombre).isPresent()){
            Cuadro cuadro = cuadroService.getByNombre(nombre).get();
            return new ResponseEntity<>(cuadro, HttpStatus.OK);
        }
        return new ResponseEntity<>(
                new Mensaje("No existe un cuadro con ese nombre."),
                HttpStatus.NOT_FOUND);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> create(
            @RequestBody CuadroDto cuadroDto){
        if(StringUtils.isBlank(cuadroDto.getNombre()))
           return new ResponseEntity<>(
                    new Mensaje("El nombre del cuadro es obligatorio."),
                    HttpStatus.BAD_REQUEST);

        if (cuadroService.existsByNombre(cuadroDto.getNombre()))
            return new ResponseEntity<>(
                    new Mensaje("El nombre del cuadro ya existe."),
                    HttpStatus.BAD_REQUEST);

        Cuadro cuadro = new Cuadro(
                cuadroDto.getNombre(),
                cuadroDto.getPintor()
        );

        Estilo estilo;
        if(estiloService.getByNombre(
                    cuadroDto.getEstilo().getNombre()).isPresent()){
            estilo = estiloService.getByNombre(
                    cuadroDto.getEstilo().getNombre()).get();
        }else {
            if(StringUtils.isBlank(cuadroDto.getEstilo().getNombre()))
                return new ResponseEntity<>(
                        new Mensaje("El nombre del estilo es obligatorio."),
                        HttpStatus.BAD_REQUEST);
            estilo = new Estilo();
            estilo.setNombre(cuadroDto.getEstilo().getNombre());
            estilo.setCuadros(new ArrayList<>());
        }
        estilo.getCuadros().add(cuadro);
        cuadro.setEstilo(estilo);

        estiloService.save(estilo);
        return new ResponseEntity<>(
                new Mensaje("Cuadro creado"),
                HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(
            @RequestBody CuadroDto cuadroDto,
            @PathVariable("id") long id){

        if (!cuadroService.existsById(id))
            return new ResponseEntity<>(
                    new Mensaje("No existe un cuadro con ese id."),
                    HttpStatus.NOT_FOUND);

        if (cuadroService.existsByNombre(
                cuadroDto.getNombre()) &&
        cuadroService.getById(id).get().getId() != id){
            return new ResponseEntity<>(
                    new Mensaje("Ya existe un cuadro con ese nombre."),
                    HttpStatus.BAD_REQUEST);
        }

        if(StringUtils.isBlank(cuadroDto.getNombre()))
            return new ResponseEntity<>(
                    new Mensaje("El nombre del cuadro es obligatorio."),
                    HttpStatus.BAD_REQUEST);

        if(cuadroService.getById(id).isPresent()) {
            Cuadro cuadro = cuadroService.getById(id).get();
            cuadro.setNombre(cuadroDto.getNombre());
            cuadro.setPintor(cuadroDto.getPintor());

            Estilo estilo;
            if(estiloService.getByNombre(
                    cuadroDto.getEstilo().getNombre()).isPresent()){
                estilo = estiloService.getByNombre(
                        cuadroDto.getEstilo().getNombre()).get();
            }else {
                if(StringUtils.isBlank(cuadroDto.getEstilo().getNombre()))
                    return new ResponseEntity<>(
                            new Mensaje("El nombre del estilo es obligatorio."),
                            HttpStatus.BAD_REQUEST);
                estilo = new Estilo();
                estilo.setNombre(cuadroDto.getEstilo().getNombre());
                estilo.setCuadros(new ArrayList<>());
            }
            estilo.getCuadros().add(cuadro);
            cuadro.setEstilo(estilo);

            estiloService.save(estilo);
            return new ResponseEntity<>(
                    new Mensaje("Cuadro actualizado"),
                    HttpStatus.OK);


        }

        return new ResponseEntity<>(
                new Mensaje("El id no existe"),
                HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        if (!cuadroService.existsById(id))
            return new ResponseEntity<>(
                    new Mensaje("No existe un cuadro con ese id"),
                    HttpStatus.NOT_FOUND);
        cuadroService.delete(id);
        return new ResponseEntity<>(
                new Mensaje("Cuadro borrado"),
                HttpStatus.OK);
    }

}
