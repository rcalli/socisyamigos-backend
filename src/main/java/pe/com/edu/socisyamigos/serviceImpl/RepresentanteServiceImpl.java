
package pe.com.edu.socisyamigos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.Representante;
import pe.com.edu.socisyamigos.repository.RepresentanteRepository;
import pe.com.edu.socisyamigos.service.RepresentanteService;

import java.util.List;
import java.util.Optional;

@Service
public class RepresentanteServiceImpl implements RepresentanteService {
    
    @Autowired
    private RepresentanteRepository representanteRepository;
    
    @Override
    public Representante create(Representante cat) {
        return representanteRepository.save(cat);
    }

    @Override
    public Representante update(Representante cat) {
        return representanteRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        representanteRepository.deleteById(id);
    }

    @Override
    public Optional<Representante> read(Long id) {
        return representanteRepository.findById(id);
    }

    @Override
    public List<Representante> readAll() {
        return representanteRepository.findAll();
    }

}
