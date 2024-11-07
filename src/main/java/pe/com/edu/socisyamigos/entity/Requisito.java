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
@Table(name="requisito")
public class Requisito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idrequisito")
    private Long id;

    @Column(name="nombre", length = 30, nullable = false)
    private String nombre;

    @Column(name="descripcion", length = 15)
    private String descripcion;

    @Column(name="estado")
    private Integer estado;
}