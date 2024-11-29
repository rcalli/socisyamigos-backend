
package pe.com.edu.socisyamigos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.edu.socisyamigos.entity.Doc;
import pe.com.edu.socisyamigos.entity.Persona;

import java.util.Optional;

/**
 *
 * Repository for Doc entity
 */
public interface DocRepository extends JpaRepository<Doc, Long> {

}
