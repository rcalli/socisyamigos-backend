
package pe.com.edu.socisyamigos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.Plan;
import pe.com.edu.socisyamigos.repository.PlanRepository;
import pe.com.edu.socisyamigos.service.PlanService;

import java.util.List;
import java.util.Optional;

@Service
public class PlanServiceImpl implements PlanService {
    
    @Autowired
    private PlanRepository planRepository;
    
    @Override
    public Plan create(Plan cat) {
        return planRepository.save(cat);
    }

    @Override
    public Plan update(Plan cat) {
        return planRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        planRepository.deleteById(id);
    }

    @Override
    public Optional<Plan> read(Long id) {
        return planRepository.findById(id);
    }

    @Override
    public List<Plan> readAll() {
        return planRepository.findAll();
    }

}
