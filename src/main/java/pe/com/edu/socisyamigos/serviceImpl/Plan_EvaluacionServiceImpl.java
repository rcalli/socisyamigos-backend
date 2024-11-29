
package pe.com.edu.socisyamigos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.Plan_Evaluacion;
import pe.com.edu.socisyamigos.repository.Plan_EvaluacionRepository;
import pe.com.edu.socisyamigos.service.Plan_EvaluacionService;

import java.util.List;
import java.util.Optional;

@Service
public class Plan_EvaluacionServiceImpl implements Plan_EvaluacionService {
    
    @Autowired
    private Plan_EvaluacionRepository planEvaluacionRepository;
    
    @Override
    public Plan_Evaluacion create(Plan_Evaluacion cat) {
        return planEvaluacionRepository.save(cat);
    }

    @Override
    public Plan_Evaluacion update(Plan_Evaluacion cat) {
        return planEvaluacionRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        planEvaluacionRepository.deleteById(id);
    }

    @Override
    public Optional<Plan_Evaluacion> read(Long id) {
        return planEvaluacionRepository.findById(id);
    }

    @Override
    public List<Plan_Evaluacion> readAll() {
        return planEvaluacionRepository.findAll();
    }

}
