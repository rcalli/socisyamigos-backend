
package pe.com.edu.socisyamigos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.Plan_Carrera;
import pe.com.edu.socisyamigos.repository.Plan_CarreraRepository;
import pe.com.edu.socisyamigos.service.Plan_CarreraService;

import java.util.List;
import java.util.Optional;

@Service
public class Plan_CarreraServiceImpl implements Plan_CarreraService {
    
    @Autowired
    private Plan_CarreraRepository planCarreraRepository;
    
    @Override
    public Plan_Carrera create(Plan_Carrera cat) {
        return planCarreraRepository.save(cat);
    }

    @Override
    public Plan_Carrera update(Plan_Carrera cat) {
        return planCarreraRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        planCarreraRepository.deleteById(id);
    }

    @Override
    public Optional<Plan_Carrera> read(Long id) {
        return planCarreraRepository.findById(id);
    }

    @Override
    public List<Plan_Carrera> readAll() {
        return planCarreraRepository.findAll();
    }

}
