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
@Table(name="supervisor")
public class Supervisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idsupervisor")
    private Long id;

    @ManyToOne
    @JoinColumn(name="idpersona", nullable = false)
    private Persona persona;

    @ManyToOne
    @JoinColumn(name="idcarrera", nullable = false)
    private Carrera carrera;

    @Column(name="estado")
    private Integer estado;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "supervisor")
    @JsonIgnore
    private Set<PPP> ppps;
}