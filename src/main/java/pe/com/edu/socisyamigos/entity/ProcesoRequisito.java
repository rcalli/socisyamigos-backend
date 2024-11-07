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
@Table(name="proceso_requisito")
public class ProcesoRequisito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idproceso_requisito")
    private Long id;

    @ManyToOne
    @JoinColumn(name="idplan_carrera", nullable = false)
    private PlanCarrera planCarrera;

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

}