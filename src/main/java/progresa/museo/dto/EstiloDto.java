package progresa.museo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstiloDto {
    @NotBlank
    private String nombre;
}
