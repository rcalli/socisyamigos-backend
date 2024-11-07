
package pe.com.edu.socisyamigos.service;

import pe.com.edu.socisyamigos.entity.Plan_Evaluacion;

import java.util.List;
import java.util.Optional;

public interface Plan_EvaluacionService {
    public Plan_Evaluacion create(Plan_Evaluacion cat);
    public Plan_Evaluacion update(Plan_Evaluacion cat);
    public void delete(Long id);
    public Optional<Plan_Evaluacion> read(Long id);
    public List<Plan_Evaluacion> readAll();
}
