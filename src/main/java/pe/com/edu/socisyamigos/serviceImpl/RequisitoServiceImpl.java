
package pe.com.edu.socisyamigos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.Requisito;
import pe.com.edu.socisyamigos.repository.RequisitoRepository;
import pe.com.edu.socisyamigos.service.RequisitoService;

import java.util.List;
import java.util.Optional;

@Service
public class RequisitoServiceImpl implements RequisitoService {
    
    @Autowired
    private RequisitoRepository requisitoRepository;
    
    @Override
    public Requisito create(Requisito cat) {
        return requisitoRepository.save(cat);
    }

    @Override
    public Requisito update(Requisito cat) {
        return requisitoRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        requisitoRepository.deleteById(id);
    }

    @Override
    public Optional<Requisito> read(Long id) {
        return requisitoRepository.findById(id);
    }

    @Override
    public List<Requisito> readAll() {
        return requisitoRepository.findAll();
    }

}
