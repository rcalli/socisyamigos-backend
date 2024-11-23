
package pe.com.edu.socisyamigos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.com.edu.socisyamigos.entity.Detalle_PPP;
import pe.com.edu.socisyamigos.entity.PPP;

import java.util.List;

/**
 *
 * Repository for Detalle_PPP entity
 */
public interface Detalle_PPPRepository extends JpaRepository<Detalle_PPP, Long> {
    List<Detalle_PPP> findByPpp(PPP ppp);
    @Query("SELECT d FROM Detalle_PPP d WHERE d.ppp.id = :pppId AND d.proceso.nombre = :procesoNombre")
    List<Detalle_PPP> findByPpp_IdAndProcesoNombre(@Param("pppId") Long pppId, @Param("procesoNombre") String procesoNombre);
}
