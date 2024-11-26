
package pe.com.edu.socisyamigos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.com.edu.socisyamigos.entity.Proceso_Requisito;

import java.util.List;

/**
 *
 * Repository for Proceso_Requisito entity
 */
public interface Proceso_RequisitoRepository extends JpaRepository<Proceso_Requisito, Long> {
    @Query("SELECT pr FROM Proceso_Requisito pr WHERE pr.plan_carrera.id = :idPlanCarrera")
    List<Proceso_Requisito> findByPlanCarreraId(@Param("idPlanCarrera") Long idPlanCarrera);
}
