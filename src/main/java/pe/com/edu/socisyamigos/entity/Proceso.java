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
@Table(name="proceso")
public class Proceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idproceso")
    private Long id;

    @Column(name="nombre", length = 20, nullable = false)
    private String nombre;

    @Column(name="descripcion", length = 20)
    private String descripcion;

    @Column(name="estado")
    private Integer estado;
}