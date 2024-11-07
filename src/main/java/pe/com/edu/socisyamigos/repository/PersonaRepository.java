package pe.com.edu.socisyamigos.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.edu.socisyamigos.entity.Estudiante;
import pe.com.edu.socisyamigos.entity.Persona;

import java.util.Optional;

/**
 *
 * @author USER
 */
public interface PersonaRepository extends JpaRepository<Persona, Long>{
    Optional<Persona> findByDni(String dni);
}