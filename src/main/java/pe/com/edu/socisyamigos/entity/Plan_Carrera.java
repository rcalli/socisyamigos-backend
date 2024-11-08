package pe.com.edu.socisyamigos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="plan_carrera")
public class Plan_Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plan_carrera_seq_gen")
    @SequenceGenerator(name = "plan_carrera_seq_gen", sequenceName = "plan_carrera_seq", allocationSize = 1)
    @Column(name = "idplan_carrera", nullable = false)
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "plan_carrera")
    @JsonIgnore
    private Set<Plan_Evaluacion> plan_evaluaciones;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "plan_carrera")
    @JsonIgnore
    private Set<Matricula> matriculas;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "plan_carrera")
    @JsonIgnore
    private Set<Proceso_Requisito> proceso_requisitos;

}