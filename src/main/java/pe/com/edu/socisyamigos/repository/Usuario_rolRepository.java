
package pe.com.edu.socisyamigos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.edu.socisyamigos.entity.Rol;
import pe.com.edu.socisyamigos.entity.Usuario;
import pe.com.edu.socisyamigos.entity.Usuario_rol;

import java.util.Optional;
import java.util.Set;

/**
 *
 * Repository for Usuario_rol entity
 */
public interface Usuario_rolRepository extends JpaRepository<Usuario_rol, Long> {
    Set<Usuario_rol> findByUsuario(Usuario usuario);
    Optional<Usuario_rol> findByUsuarioAndRol(Usuario usuario, Rol rol);
}
