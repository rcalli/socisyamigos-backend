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
@Table(name="usuario_rol")
public class Usuario_rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario_rol")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idusuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idrol", nullable = false)
    private Rol rol;

    @Column(name = "estado")
    private Integer estado;

}
