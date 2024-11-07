
package pe.com.edu.socisyamigos.service;

import pe.com.edu.socisyamigos.entity.Acceso_rol;

import java.util.List;
import java.util.Optional;

public interface Acceso_rolService {
    public Acceso_rol create(Acceso_rol cat);
    public Acceso_rol update(Acceso_rol cat);
    public void delete(Long id);
    public Optional<Acceso_rol> read(Long id);
    public List<Acceso_rol> readAll();
}
