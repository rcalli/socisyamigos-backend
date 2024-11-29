
package pe.com.edu.socisyamigos.service;

import pe.com.edu.socisyamigos.entity.Acceso;

import java.util.List;
import java.util.Optional;

public interface AccesoService {
    public Acceso create(Acceso cat);
    public Acceso update(Acceso cat);
    public void delete(Long id);
    public Optional<Acceso> read(Long id);
    public List<Acceso> readAll();
}
