
package pe.com.edu.socisyamigos.service;

import pe.com.edu.socisyamigos.entity.Proceso_Requisito;

import java.util.List;
import java.util.Optional;

public interface Proceso_RequisitoService {
    public Proceso_Requisito create(Proceso_Requisito cat);
    public Proceso_Requisito update(Proceso_Requisito cat);
    public void delete(Long id);
    public Optional<Proceso_Requisito> read(Long id);
    public List<Proceso_Requisito> readAll();
}
