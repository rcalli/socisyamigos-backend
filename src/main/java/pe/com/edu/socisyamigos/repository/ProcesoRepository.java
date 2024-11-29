
package pe.com.edu.socisyamigos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.com.edu.socisyamigos.entity.Proceso;

import java.util.Optional;

/**
 *
 * Repository for Proceso entity
 */
public interface ProcesoRepository extends JpaRepository<Proceso, Long> {
    @Query("SELECT p.id FROM Proceso p WHERE p.nombre = :nombreProceso")
    Optional<Long> findIdByNombre(@Param("nombreProceso") String nombreProceso);
}
