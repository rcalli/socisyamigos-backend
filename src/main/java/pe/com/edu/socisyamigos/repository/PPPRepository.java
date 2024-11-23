
package pe.com.edu.socisyamigos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.com.edu.socisyamigos.entity.PPP;

import java.util.List;
import java.util.Map;

/**
 *
 * Repository for PPP entity
 */
public interface PPPRepository extends JpaRepository<PPP, Long> {
    @Query(value = """
        SELECT ppp.id AS idPPP,
               ppp.matricula.estudiante.persona.nombre AS nombreEstudiante,
               ppp.empresa.nombre AS nombreEmpresa,
               ppp.matricula.estudiante.codigo AS codigoEstudiante,
               ppp.estado AS estadoPPP
        FROM PPP ppp
        JOIN Detalle_PPP detalle ON detalle.ppp.id = ppp.id
        JOIN Requisito req ON detalle.requisito.id = req.id
        WHERE detalle.proceso.id = :idProceso
          AND detalle.estado != 0
          AND ppp.estado = 1 OR ppp.estado = 3 OR ppp.estado = 4
        GROUP BY ppp.id, ppp.matricula.estudiante.persona.nombre, ppp.empresa.nombre, ppp.matricula.estudiante.codigo, ppp.estado
    """)
    List<Map<String, Object>> findFilteredPPPsByProcessId(@Param("idProceso") Long idProceso);
    @Query("SELECT p FROM PPP p WHERE p.estado IN :estados")
    List<PPP> findByEstado(@Param("estados") List<Integer> estados);
}
