package pe.com.edu.socisyamigos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="matricula")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmatricula", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="idestudiante", nullable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name="idplan_carrera", nullable = false)
    private Plan_Carrera plan_carrera;

    @Column(name="horas_totales")
    private Integer horas_totales;

    @Column(name="anio")
    private Integer anio;

    @Column(name="estado")
    private Integer estado;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "matricula")
    @JsonIgnore
    private Set<PPP> ppps;

}