
package pe.com.edu.socisyamigos.service;

import pe.com.edu.socisyamigos.entity.Recurso;

import java.util.List;
import java.util.Optional;

public interface RecursoService {
    public Recurso create(Recurso cat);
    public Recurso update(Recurso cat);
    public void delete(Long id);
    public Optional<Recurso> read(Long id);
    public List<Recurso> readAll();
}
