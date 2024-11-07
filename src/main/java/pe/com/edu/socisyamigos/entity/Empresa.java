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
@Table(name="empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idempresa")
    private Long id;

    @Column(name="nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name="direccion", length = 64)
    private String direccion;

    @Column(name="telefono", length = 15)
    private String telefono;

    @Column(name="email", length = 50)
    private String email;

    @Column(name="estado")
    private Integer estado;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "empresa")
    @JsonIgnore
    private Set<Representante> representantes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "empresa")
    @JsonIgnore
    private Set<PPP> ppps;

}