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
@Table(name="linea_carrera")
public class Linea_Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idlinea_carrera")
    private Long id;

    @ManyToOne
    @JoinColumn(name="idcarrera", nullable = true)
    private Carrera carrera;

    @Column(name="nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name="estado")
    private Integer estado;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "linea_carrera")
    @JsonIgnore
    private Set<PPP> ppps;
}