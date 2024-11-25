
package pe.com.edu.socisyamigos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.com.edu.socisyamigos.entity.PPP_Evaluacion;

import java.util.List;

/**
 *
 * Repository for PPP_Evaluacion entity
 */
public interface PPP_EvaluacionRepository extends JpaRepository<PPP_Evaluacion, Long> {
    List<PPP_Evaluacion> findByPppId(Long pppId);
    boolean existsByPppIdAndEvaluacionId(Long pppId, Long evaluacionId);
    @Query("SELECT pe FROM PPP_Evaluacion pe " +
            "JOIN pe.ppp p " +
            "JOIN p.supervisor s " +
            "WHERE s.persona.idpersona = :idPersona")
    List<PPP_Evaluacion> findBySupervisorPersonaId(@Param("idPersona") Long idPersona);
}
