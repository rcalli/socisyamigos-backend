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
@Table(name="plan_evaluacion")
public class Plan_Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idplan_evaluacion")
    private Long id;

    @ManyToOne
    @JoinColumn(name="idplan_carrera", nullable = false)
    private Plan_Carrera plan_carrera;

    @ManyToOne
    @JoinColumn(name="idevaluacion", nullable = false)
    private Evaluacion evaluacion;

    @Column(name="peso")
    private Integer peso;

    @Column(name="estado")
    private Integer estado;
}