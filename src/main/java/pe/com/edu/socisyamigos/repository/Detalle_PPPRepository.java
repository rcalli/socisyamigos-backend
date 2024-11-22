
package pe.com.edu.socisyamigos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.edu.socisyamigos.entity.Detalle_PPP;
import pe.com.edu.socisyamigos.entity.PPP;

import java.util.List;

/**
 *
 * Repository for Detalle_PPP entity
 */
public interface Detalle_PPPRepository extends JpaRepository<Detalle_PPP, Long> {
    List<Detalle_PPP> findByPpp(PPP ppp);
    List<Detalle_PPP> findByPpp_Id(Long pppId);
}
