package pe.com.edu.socisyamigos.entity;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpersona", nullable = false)
    private Long idpersona;

    @Column(name="nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name="apepat", length = 50, nullable = false)
    private String apepat;

    @Column(name="apemat", length = 50, nullable = false)
    private String apemat;

    @Column(name="direccion", length = 50)
    private String direccion;

    @Column(name="dni", length = 8, unique = true, nullable = false)
    private String dni;

    @Column(name="correo", length = 50)
    private String correo;

    @Column(name="telefono", length = 9)
    private String telefono;

    @Column(name="estado")
    private Integer estado;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "persona")
    @JsonIgnore
    private Set<Usuario> usuarios;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "persona")
    @JsonIgnore
    private Set<Detalle_PPP> detalle_ppps;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "persona")
    @JsonIgnore
    private Set<Representante> representantes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "persona")
    @JsonIgnore
    private Set<Supervisor> supervisores;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "persona")
    @JsonIgnore
    private Set<Estudiante> estudiantes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "persona")
    @JsonIgnore
    private Set<Plan_Carrera> plan_carreras;

}