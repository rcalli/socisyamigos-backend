
package pe.com.edu.socisyamigos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.Detalle_PPP;
import pe.com.edu.socisyamigos.repository.Detalle_PPPRepository;
import pe.com.edu.socisyamigos.service.Detalle_PPPService;

import java.util.List;
import java.util.Optional;

@Service
public class Detalle_PPPServiceImpl implements Detalle_PPPService {
    
    @Autowired
    private Detalle_PPPRepository detallePppRepository;
    
    @Override
    public Detalle_PPP create(Detalle_PPP cat) {
        return detallePppRepository.save(cat);
    }

    @Override
    public Detalle_PPP update(Detalle_PPP cat) {
        return detallePppRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        detallePppRepository.deleteById(id);
    }

    @Override
    public Optional<Detalle_PPP> read(Long id) {
        return detallePppRepository.findById(id);
    }

    @Override
    public List<Detalle_PPP> readAll() {
        return detallePppRepository.findAll();
    }

}
