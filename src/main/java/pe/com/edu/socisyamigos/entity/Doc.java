package pe.com.edu.socisyamigos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="doc")
public class Doc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="iddoc")
    private Long id;

    @ManyToOne
    @JoinColumn(name="iddetalle_ppp", nullable = false)
    private Detalle_PPP detalle_ppp;

    @Column(name="nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name="ruta_archivo", length = 200, nullable = false)
    private String ruta_archivo;

    @Column(name="fecha")
    private Date fecha;

    @Column(name="estado")
    private Integer estado;

}