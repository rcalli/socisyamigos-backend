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
@Table(name="linea_carrera")
public class LineaCarrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idlinea_carrera")
    private Long id;

    @ManyToOne
    @JoinColumn(name="idcarrera", nullable = false)
    private Carrera carrera;

    @Column(name="nombre", length = 20, nullable = false)
    private String nombre;

    @Column(name="estado")
    private Integer estado;
}