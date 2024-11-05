package progresa.museo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpleadoDto {
    @NotBlank
    private String nombre;
    private int edad;
    private ProfesionDto profesion;
    private DireccionDto direccion;
}
