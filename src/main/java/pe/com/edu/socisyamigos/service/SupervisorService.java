
package pe.com.edu.socisyamigos.service;

import pe.com.edu.socisyamigos.entity.Supervisor;

import java.util.List;
import java.util.Optional;

public interface SupervisorService {
    public Supervisor create(Supervisor cat);
    public Supervisor update(Supervisor cat);
    public void delete(Long id);
    public Optional<Supervisor> read(Long id);
    public List<Supervisor> readAll();
}
