
package pe.com.edu.socisyamigos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.edu.socisyamigos.entity.Linea_Carrera;

import java.util.List;

/**
 *
 * Repository for Linea_Carrera entity
 */
public interface Linea_CarreraRepository extends JpaRepository<Linea_Carrera, Long> {
    List<Linea_Carrera> findByCarreraId(Long idCarrera);
}
