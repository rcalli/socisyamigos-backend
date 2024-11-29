
package pe.com.edu.socisyamigos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.Evaluacion;
import pe.com.edu.socisyamigos.repository.EvaluacionRepository;
import pe.com.edu.socisyamigos.service.EvaluacionService;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluacionServiceImpl implements EvaluacionService {
    
    @Autowired
    private EvaluacionRepository evaluacionRepository;
    
    @Override
    public Evaluacion create(Evaluacion cat) {
        return evaluacionRepository.save(cat);
    }

    @Override
    public Evaluacion update(Evaluacion cat) {
        return evaluacionRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        evaluacionRepository.deleteById(id);
    }

    @Override
    public Optional<Evaluacion> read(Long id) {
        return evaluacionRepository.findById(id);
    }

    @Override
    public List<Evaluacion> readAll() {
        return evaluacionRepository.findAll();
    }

}
