package pe.com.edu.socisyamigos.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.com.edu.socisyamigos.entity.Persona;
import pe.com.edu.socisyamigos.entity.Usuario;

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
}