
package pe.com.edu.socisyamigos.service;

import pe.com.edu.socisyamigos.entity.Evaluacion;

import java.util.List;
import java.util.Optional;

public interface EvaluacionService {
    public Evaluacion create(Evaluacion cat);
    public Evaluacion update(Evaluacion cat);
    public void delete(Long id);
    public Optional<Evaluacion> read(Long id);
    public List<Evaluacion> readAll();
}
