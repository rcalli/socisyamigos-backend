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
@Table(name="matricula")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idmatricula")
    private Long id;

    @ManyToOne
    @JoinColumn(name="idestudiante", nullable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name="idplan_carrera", nullable = false)
    private PlanCarrera planCarrera;

    @Column(name="horas_totales")
    private Integer horasTotales;

    @Column(name="anio")
    private Integer anio;

    @Column(name="estado")
    private Integer estado;

}