package pe.com.edu.socisyamigos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="recurso")
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idrecurso")
    private Long id;

    @ManyToOne
    @JoinColumn(name="idproceso_requisito", nullable = false)
    private Proceso_Requisito proceso_requisito;

    @Column(name="nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name="ruta_archivo", length = 200, nullable = false)
    private String ruta_archivo;

    @Column(name="estado")
    private Integer estado;

}