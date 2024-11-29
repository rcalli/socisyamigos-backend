
package pe.com.edu.socisyamigos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.Recurso;
import pe.com.edu.socisyamigos.repository.RecursoRepository;
import pe.com.edu.socisyamigos.service.RecursoService;

import java.util.List;
import java.util.Optional;

@Service
public class RecursoServiceImpl implements RecursoService {
    
    @Autowired
    private RecursoRepository recursoRepository;
    
    @Override
    public Recurso create(Recurso cat) {
        return recursoRepository.save(cat);
    }

    @Override
    public Recurso update(Recurso cat) {
        return recursoRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        recursoRepository.deleteById(id);
    }

    @Override
    public Optional<Recurso> read(Long id) {
        return recursoRepository.findById(id);
    }

    @Override
    public List<Recurso> readAll() {
        return recursoRepository.findAll();
    }

}
