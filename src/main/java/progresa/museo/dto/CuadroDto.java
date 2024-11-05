package progresa.museo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuadroDto {
    @NotBlank
    private String nombre;

    private String pintor;
    private EstiloDto estilo;
}
