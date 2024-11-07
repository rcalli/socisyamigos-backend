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
@Table(name="representante")
public class Representante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idrepresentante")
    private Long id;

    @ManyToOne
    @JoinColumn(name="idpersona", nullable = false)
    private Persona persona;

    @ManyToOne
    @JoinColumn(name="idempresa", nullable = false)
    private Empresa empresa;

    @Column(name="cargo", length = 30, nullable = false)
    private String cargo;

    @Column(name="estado")
    private Integer estado;

}