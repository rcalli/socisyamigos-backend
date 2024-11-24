
package pe.com.edu.socisyamigos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.edu.socisyamigos.entity.Supervisor;

import java.util.List;

/**
 *
 * Repository for Supervisor entity
 */
public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {
    // Buscar supervisores por carrera
    List<Supervisor> findByCarreraId(Long carreraId);
}
