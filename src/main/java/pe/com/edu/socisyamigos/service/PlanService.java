
package pe.com.edu.socisyamigos.service;

import pe.com.edu.socisyamigos.entity.Plan;

import java.util.List;
import java.util.Optional;

public interface PlanService {
    public Plan create(Plan cat);
    public Plan update(Plan cat);
    public void delete(Long id);
    public Optional<Plan> read(Long id);
    public List<Plan> readAll();
}
