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
@Table(name="proceso_requisito")
public class Proceso_Requisito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idproceso_requisito")
    private Long id;

    @ManyToOne
    @JoinColumn(name="idplan_carrera", nullable = false)
    private Plan_Carrera plan_carrera;

    @ManyToOne
    @JoinColumn(name="idproceso", nullable = false)
    private Proceso proceso;

    @ManyToOne
    @JoinColumn(name="idrequisito", nullable = false)
    private Requisito requisito;

    @ManyToOne
    @JoinColumn(name="idrol", nullable = false)
    private Rol rol;

    @Column(name="orden")
    private Integer orden;

    @Column(name="estado")
    private Integer estado;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "proceso_requisito")
    @JsonIgnore
    private Set<Recurso> recursos;

}