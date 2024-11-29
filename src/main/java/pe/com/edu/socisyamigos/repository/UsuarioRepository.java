package pe.com.edu.socisyamigos.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.com.edu.socisyamigos.entity.Persona;
import pe.com.edu.socisyamigos.entity.Rol;
import pe.com.edu.socisyamigos.entity.Usuario;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author USER
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByPersona(Persona persona);
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findById(Long id);
    @Query("SELECT u.id FROM Usuario u WHERE u.username = :username")
    Optional<Long> findIdByUsername(String username);
    @Query("SELECT r FROM Rol r " +
            "JOIN Usuario_rol ur ON ur.rol.id = r.id " +
            "JOIN Usuario u ON ur.usuario.id = u.id " +
            "WHERE u.username = :username")
    List<Rol> findRolesByUsername(String username);
}