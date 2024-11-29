
package pe.com.edu.socisyamigos.service;

import pe.com.edu.socisyamigos.entity.Carrera;

import java.util.List;
import java.util.Optional;

public interface CarreraService {
    public Carrera create(Carrera cat);
    public Carrera update(Carrera cat);
    public void delete(Long id);
    public Optional<Carrera> read(Long id);
    public List<Carrera> readAll();
}
