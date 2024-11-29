
package pe.com.edu.socisyamigos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.Proceso_Requisito;
import pe.com.edu.socisyamigos.repository.Proceso_RequisitoRepository;
import pe.com.edu.socisyamigos.service.Proceso_RequisitoService;

import java.util.List;
import java.util.Optional;

@Service
public class Proceso_RequisitoServiceImpl implements Proceso_RequisitoService {
    
    @Autowired
    private Proceso_RequisitoRepository procesoRequisitoRepository;
    
    @Override
    public Proceso_Requisito create(Proceso_Requisito cat) {
        return procesoRequisitoRepository.save(cat);
    }

    @Override
    public Proceso_Requisito update(Proceso_Requisito cat) {
        return procesoRequisitoRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        procesoRequisitoRepository.deleteById(id);
    }

    @Override
    public Optional<Proceso_Requisito> read(Long id) {
        return procesoRequisitoRepository.findById(id);
    }

    @Override
    public List<Proceso_Requisito> readAll() {
        return procesoRequisitoRepository.findAll();
    }

}
