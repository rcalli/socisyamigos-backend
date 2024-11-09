
package pe.com.edu.socisyamigos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.PPP_Evaluacion;
import pe.com.edu.socisyamigos.repository.PPP_EvaluacionRepository;
import pe.com.edu.socisyamigos.service.PPP_EvaluacionService;

import java.util.List;
import java.util.Optional;

@Service
public class PPP_EvaluacionServiceImpl implements PPP_EvaluacionService {
    
    @Autowired
    private PPP_EvaluacionRepository pppEvaluacionRepository;
    
    @Override
    public PPP_Evaluacion create(PPP_Evaluacion cat) {
        return pppEvaluacionRepository.save(cat);
    }

    @Override
    public PPP_Evaluacion update(PPP_Evaluacion cat) {
        return pppEvaluacionRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        pppEvaluacionRepository.deleteById(id);
    }

    @Override
    public Optional<PPP_Evaluacion> read(Long id) {
        return pppEvaluacionRepository.findById(id);
    }

    @Override
    public List<PPP_Evaluacion> readAll() {
        return pppEvaluacionRepository.findAll();
    }

}
