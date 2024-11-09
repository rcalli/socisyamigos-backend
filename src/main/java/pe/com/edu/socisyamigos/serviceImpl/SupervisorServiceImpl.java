
package pe.com.edu.socisyamigos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.Supervisor;
import pe.com.edu.socisyamigos.repository.SupervisorRepository;
import pe.com.edu.socisyamigos.service.SupervisorService;

import java.util.List;
import java.util.Optional;

@Service
public class SupervisorServiceImpl implements SupervisorService {
    
    @Autowired
    private SupervisorRepository supervisorRepository;
    
    @Override
    public Supervisor create(Supervisor cat) {
        return supervisorRepository.save(cat);
    }

    @Override
    public Supervisor update(Supervisor cat) {
        return supervisorRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        supervisorRepository.deleteById(id);
    }

    @Override
    public Optional<Supervisor> read(Long id) {
        return supervisorRepository.findById(id);
    }

    @Override
    public List<Supervisor> readAll() {
        return supervisorRepository.findAll();
    }

}
