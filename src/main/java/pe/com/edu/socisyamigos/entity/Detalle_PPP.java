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
@Table(name="detalle_ppp")
public class Detalle_PPP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="iddetalle_ppp")
    private Long id;

    @ManyToOne
    @JoinColumn(name="idppp", nullable = false)
    private PPP ppp;

    @ManyToOne
    @JoinColumn(name="idproceso", nullable = false)
    private Proceso proceso;

    @ManyToOne
    @JoinColumn(name="idrequisito", nullable = false)
    private Requisito requisito;

    @ManyToOne
    @JoinColumn(name="idpersona", nullable = false)
    private Persona persona;

    @Column(name="orden")
    private Integer orden;

    @Column(name="estado")
    private Integer estado;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "detalle_ppp")
    @JsonIgnore
    private Set<Doc> docs;

}