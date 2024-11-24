
package pe.com.edu.socisyamigos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.edu.socisyamigos.entity.PPP_Evaluacion;

import java.util.List;

/**
 *
 * Repository for PPP_Evaluacion entity
 */
public interface PPP_EvaluacionRepository extends JpaRepository<PPP_Evaluacion, Long> {
    List<PPP_Evaluacion> findByPppId(Long pppId);
    boolean existsByPppIdAndEvaluacionId(Long pppId, Long evaluacionId);
}
