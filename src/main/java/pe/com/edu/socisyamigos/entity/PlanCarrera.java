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
@Table(name="plan_carrera")
public class PlanCarrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idplan_carrera")
    private Long id;

    @ManyToOne
    @JoinColumn(name="idplan", nullable = false)
    private Plan plan;

    @ManyToOne
    @JoinColumn(name="idcarrera", nullable = false)
    private Carrera carrera;

    @ManyToOne
    @JoinColumn(name="idpersona", nullable = false)
    private Persona persona;

    @Column(name="n_carta")
    private Integer nCarta;

    @Column(name="estado")
    private Integer estado;

}