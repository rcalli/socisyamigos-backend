
package pe.com.edu.socisyamigos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.edu.socisyamigos.entity.Plan;

import java.util.Optional;

/**
 *
 * Repository for Plan entity
 */
public interface PlanRepository extends JpaRepository<Plan, Long> {
    Optional<Plan> findByNombre(String nombre);
    Optional<Plan> findByNombreAndAnioAndEtapaAndEstado(String nombre, int anio, int etapa, int estado);
}
