
package pe.com.edu.socisyamigos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.Proceso;
import pe.com.edu.socisyamigos.repository.ProcesoRepository;
import pe.com.edu.socisyamigos.service.ProcesoService;

import java.util.List;
import java.util.Optional;

@Service
public class ProcesoServiceImpl implements ProcesoService {
    
    @Autowired
    private ProcesoRepository procesoRepository;
    
    @Override
    public Proceso create(Proceso cat) {
        return procesoRepository.save(cat);
    }

    @Override
    public Proceso update(Proceso cat) {
        return procesoRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        procesoRepository.deleteById(id);
    }

    @Override
    public Optional<Proceso> read(Long id) {
        return procesoRepository.findById(id);
    }

    @Override
    public List<Proceso> readAll() {
        return procesoRepository.findAll();
    }

}
