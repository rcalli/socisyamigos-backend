
package pe.com.edu.socisyamigos.service;

import pe.com.edu.socisyamigos.entity.Usuario_rol;

import java.util.List;
import java.util.Optional;

public interface Usuario_rolService {
    public Usuario_rol create(Usuario_rol cat);
    public Usuario_rol update(Usuario_rol cat);
    public void delete(Long id);
    public Optional<Usuario_rol> read(Long id);
    public List<Usuario_rol> readAll();
}
