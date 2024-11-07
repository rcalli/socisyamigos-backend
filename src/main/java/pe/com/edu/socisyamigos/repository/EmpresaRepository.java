
package pe.com.edu.socisyamigos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.edu.socisyamigos.entity.Empresa;

/**
 *
 * Repository for Empresa entity
 */
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
