package pe.com.edu.socisyamigos.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class CrearPPPDto {
    private Long idMatricula; // ID de la matrícula
    private Long idLineaCarrera; // ID de la línea de carrera seleccionada
    private Long idEmpresa; // ID de la empresa seleccionada
}
