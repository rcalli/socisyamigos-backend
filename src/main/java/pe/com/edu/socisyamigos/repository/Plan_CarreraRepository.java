
package pe.com.edu.socisyamigos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.com.edu.socisyamigos.entity.Carrera;
import pe.com.edu.socisyamigos.entity.Persona;
import pe.com.edu.socisyamigos.entity.Plan;
import pe.com.edu.socisyamigos.entity.Plan_Carrera;

import java.util.Optional;

/**
 *
 * Repository for Plan_Carrera entity
 */
public interface Plan_CarreraRepository extends JpaRepository<Plan_Carrera, Long> {
    Optional<Plan_Carrera> findByPlanIdAndCarreraId(Long planId, Long carreraId);
    Optional<Plan_Carrera> findByPlanAndCarreraAndPersona(Plan plan, Carrera carrera, Persona persona);
    @Query("SELECT pc FROM Plan_Carrera pc WHERE pc.plan = :plan AND pc.carrera = :carrera")
    Optional<Plan_Carrera> findByPlanAndCarrera(@Param("plan") Plan plan, @Param("carrera") Carrera carrera);
}
