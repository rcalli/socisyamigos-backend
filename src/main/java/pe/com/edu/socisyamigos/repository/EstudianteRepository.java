package pe.com.edu.socisyamigos.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.edu.socisyamigos.entity.Estudiante;

import java.util.Optional;

/**
 *
 * @author USER
 */
public interface EstudianteRepository extends JpaRepository<Estudiante, Long>{
    Optional<Estudiante> findByCodigo(String codigo);
}