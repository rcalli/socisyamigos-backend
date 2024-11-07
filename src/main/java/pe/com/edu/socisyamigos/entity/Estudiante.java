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
@Table(name="estudiante")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idestudiante")
    private Long id;

    @ManyToOne
    @JoinColumn(name="idpersona", nullable = false)
    private Persona persona;

    @Column(name="codigo", length = 15, nullable = false)
    private String codigo;

    @Column(name="estado")
    private Integer estado;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "estudiante")
    @JsonIgnore
    private Set<Matricula> matriculas;

}