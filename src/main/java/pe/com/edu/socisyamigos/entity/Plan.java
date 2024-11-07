package pe.com.edu.socisyamigos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="plan")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idplan")
    private Long id;

    @Column(name="nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name="anio")
    private Integer anio;

    @Column(name="etapa")
    private Integer etapa;

    @Column(name="estado")
    private Integer estado;
}