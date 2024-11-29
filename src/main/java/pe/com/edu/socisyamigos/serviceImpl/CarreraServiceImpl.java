
package pe.com.edu.socisyamigos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.Carrera;
import pe.com.edu.socisyamigos.repository.CarreraRepository;
import pe.com.edu.socisyamigos.service.CarreraService;

import java.util.List;
import java.util.Optional;

@Service
public class CarreraServiceImpl implements CarreraService {
    
    @Autowired
    private CarreraRepository carreraRepository;
    
    @Override
    public Carrera create(Carrera cat) {
        return carreraRepository.save(cat);
    }

    @Override
    public Carrera update(Carrera cat) {
        return carreraRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        carreraRepository.deleteById(id);
    }

    @Override
    public Optional<Carrera> read(Long id) {
        return carreraRepository.findById(id);
    }

    @Override
    public List<Carrera> readAll() {
        return carreraRepository.findAll();
    }

}
