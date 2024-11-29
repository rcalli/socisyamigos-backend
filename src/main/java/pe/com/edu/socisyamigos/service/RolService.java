
package pe.com.edu.socisyamigos.service;

import pe.com.edu.socisyamigos.entity.Rol;

import java.util.List;
import java.util.Optional;

public interface RolService {
    public Rol create(Rol cat);
    public Rol update(Rol cat);
    public void delete(Long id);
    public Optional<Rol> read(Long id);
    public List<Rol> readAll();
}
