
package pe.com.edu.socisyamigos.service;

import pe.com.edu.socisyamigos.entity.Representante;

import java.util.List;
import java.util.Optional;

public interface RepresentanteService {
    public Representante create(Representante cat);
    public Representante update(Representante cat);
    public void delete(Long id);
    public Optional<Representante> read(Long id);
    public List<Representante> readAll();
}
