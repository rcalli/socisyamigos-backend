
package pe.com.edu.socisyamigos.service;

import pe.com.edu.socisyamigos.entity.Matricula;

import java.util.List;
import java.util.Optional;

public interface MatriculaService {
    public Matricula create(Matricula cat);
    public Matricula update(Matricula cat);
    public void delete(Long id);
    public Optional<Matricula> read(Long id);
    public List<Matricula> readAll();
}
