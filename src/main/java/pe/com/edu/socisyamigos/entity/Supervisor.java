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
}