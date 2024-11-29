
package pe.com.edu.socisyamigos.service;

import pe.com.edu.socisyamigos.entity.Requisito;

import java.util.List;
import java.util.Optional;

public interface RequisitoService {
    public Requisito create(Requisito cat);
    public Requisito update(Requisito cat);
    public void delete(Long id);
    public Optional<Requisito> read(Long id);
    public List<Requisito> readAll();
}
