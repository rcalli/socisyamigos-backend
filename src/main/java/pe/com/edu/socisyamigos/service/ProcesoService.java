
package pe.com.edu.socisyamigos.service;

import pe.com.edu.socisyamigos.entity.Proceso;

import java.util.List;
import java.util.Optional;

public interface ProcesoService {
    public Proceso create(Proceso cat);
    public Proceso update(Proceso cat);
    public void delete(Long id);
    public Optional<Proceso> read(Long id);
    public List<Proceso> readAll();
}
