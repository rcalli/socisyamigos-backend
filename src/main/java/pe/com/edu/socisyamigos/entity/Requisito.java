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
@Table(name="requisito")
public class Requisito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idrequisito")
    private Long id;

    @Column(name="nombre", length = 30, nullable = false)
    private String nombre;

    @Column(name="descripcion", length = 200)
    private String descripcion;

    @Column(name="estado")
    private Integer estado;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "requisito")
    @JsonIgnore
    private Set<Proceso_Requisito> proceso_requisitos;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "requisito")
    @JsonIgnore
    private Set<Detalle_PPP> detalle_ppps;
}