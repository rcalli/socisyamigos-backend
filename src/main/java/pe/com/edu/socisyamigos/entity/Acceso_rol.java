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
@Table(name="acceso_rol")
public class Acceso_rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idacceso_rol")
    private Long id;

    @ManyToOne
    @JoinColumn(name="idrol", nullable = false)
    private Rol rol;

    @ManyToOne
    @JoinColumn(name="idacceso", nullable = false)
    private Acceso acceso;

    @Column(name="estado")
    private Integer estado;
}
