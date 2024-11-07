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
@Table(name="acceso")
public class Acceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idacceso")
    private Long id;

    @Column(name="nombre", length = 30, nullable = false)
    private String nombre;

    @Column(name="estado")
    private Integer estado;
}