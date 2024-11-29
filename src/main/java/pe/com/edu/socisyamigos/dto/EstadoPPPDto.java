package pe.com.edu.socisyamigos.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class EstadoPPPDto {
    private int estadoPPP; // Estado para la PPP
    private int estadoDetallePPP; // Estado para los detalles de PPP
    private String procesoNombre; // Nombre del proceso para filtrar los detalles
}
