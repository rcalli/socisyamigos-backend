package pe.com.edu.socisyamigos.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="ppp")
public class PPP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idppp")
    private Long id;

    @ManyToOne
    @JoinColumn(name="idempresa", nullable = false)
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name="idlinea_carrera", nullable = false)
    private Linea_Carrera linea_carrera;

    @ManyToOne
    @JoinColumn(name="idmatricula", nullable = false)
    private Matricula matricula;

    @ManyToOne
    @JoinColumn(name="idsupervisor", nullable = true)
    private Supervisor supervisor;

    @Column(name="fecha_inicio")
    private Date fechaInicio;

    @Column(name="fecha_fin")
    private Date fechaFin;

    @Column(name="horas")
    private Integer horas;

    @Column(name="promedio")
    private Double promedio;

    @Column(name="estado")
    private Integer estado;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ppp")
    @JsonIgnore
    private Set<PPP_Evaluacion> ppp_evaluaciones;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ppp")
    @JsonIgnore
    private Set<Detalle_PPP> detalle_ppps;

}