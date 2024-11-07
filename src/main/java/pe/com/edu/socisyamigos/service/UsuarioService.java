
package pe.com.edu.socisyamigos.service;

import pe.com.edu.socisyamigos.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    public Usuario create(Usuario cat);
    public Usuario update(Usuario cat);
    public void delete(Long id);
    public Optional<Usuario> read(Long id);
    public List<Usuario> readAll();
}
