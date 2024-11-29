
package pe.com.edu.socisyamigos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.com.edu.socisyamigos.entity.Estudiante;
import pe.com.edu.socisyamigos.entity.Matricula;
import pe.com.edu.socisyamigos.entity.Plan_Carrera;

import java.util.List;
import java.util.Optional;

/**
 *
 * Repository for Matricula entity
 */
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    @Query("SELECT m FROM Matricula m WHERE m.estudiante = :estudiante AND m.plan_carrera = :planCarrera AND m.anio = :anio")
    Optional<Matricula> findByEstudianteAndPlanCarreraAndAnio(@Param("estudiante") Estudiante estudiante,
                                                              @Param("planCarrera") Plan_Carrera planCarrera,
                                                              @Param("anio") int anio);
    @Query("SELECT m FROM Matricula m WHERE m.estudiante.persona.idpersona = :idPersona")
    List<Matricula> findByPersonaId(@Param("idPersona") Long idPersona);
}
