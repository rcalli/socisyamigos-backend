
package pe.com.edu.socisyamigos.service;

import pe.com.edu.socisyamigos.entity.Plan_Carrera;

import java.util.List;
import java.util.Optional;

public interface Plan_CarreraService {
    public Plan_Carrera create(Plan_Carrera cat);
    public Plan_Carrera update(Plan_Carrera cat);
    public void delete(Long id);
    public Optional<Plan_Carrera> read(Long id);
    public List<Plan_Carrera> readAll();
}
