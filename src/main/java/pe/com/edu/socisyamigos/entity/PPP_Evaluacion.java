package pe.com.edu.socisyamigos.entity;

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
@Table(name="ppp_evaluacion")
public class PPP_Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idppp_evaluacion")
    private Long id;

    @ManyToOne
    @JoinColumn(name="idppp", nullable = false)
    private PPP ppp;

    @ManyToOne
    @JoinColumn(name="idevaluacion", nullable = false)
    private Evaluacion evaluacion;

    @Column(name="fecha_registro")
    private Date fecha_registro;

    @Column(name="nota")
    private Integer nota;

    @Column(name="ruta_archivo", length = 200, nullable = false)
    private String ruta_archivo;

    @Column(name="evidencia", length = 200, nullable = false)
    private Double evidencia;

    @Column(name="estado")
    private Integer estado;

}