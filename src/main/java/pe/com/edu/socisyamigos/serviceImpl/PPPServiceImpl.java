
package pe.com.edu.socisyamigos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.PPP;
import pe.com.edu.socisyamigos.repository.PPPRepository;
import pe.com.edu.socisyamigos.service.PPPService;

import java.util.List;
import java.util.Optional;

@Service
public class PPPServiceImpl implements PPPService {
    
    @Autowired
    private PPPRepository pppRepository;
    
    @Override
    public PPP create(PPP cat) {
        return pppRepository.save(cat);
    }

    @Override
    public PPP update(PPP cat) {
        return pppRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        pppRepository.deleteById(id);
    }

    @Override
    public Optional<PPP> read(Long id) {
        return pppRepository.findById(id);
    }

    @Override
    public List<PPP> readAll() {
        return pppRepository.findAll();
    }

}
