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
@Table(name="rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idrol")
    private Long id;

    @Column(name="nombre", length = 30, nullable = false)
    private String nombre;

    @Column(name="url", length = 200, nullable = false)
    private String url;

    @Column(name="estado")
    private Integer estado;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rol")
    @JsonIgnore
    private Set<Acceso_rol> acceso_rol;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rol")
    @JsonIgnore
    private Set<Usuario_rol> usuario_rol;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rol")
    @JsonIgnore
    private Set<Proceso_Requisito> procesor_equisitos;
}