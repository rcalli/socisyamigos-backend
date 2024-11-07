package pe.com.edu.socisyamigos.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.edu.socisyamigos.entity.Estudiante;
import pe.com.edu.socisyamigos.entity.Persona;

/**
 *
 * @author USER
 */
public interface PersonaRepository extends JpaRepository<Persona, Long>{

}