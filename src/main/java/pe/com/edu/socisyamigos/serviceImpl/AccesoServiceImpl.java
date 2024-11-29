
package pe.com.edu.socisyamigos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.Acceso;
import pe.com.edu.socisyamigos.repository.AccesoRepository;
import pe.com.edu.socisyamigos.service.AccesoService;

import java.util.List;
import java.util.Optional;

@Service
public class AccesoServiceImpl implements AccesoService {
    
    @Autowired
    private AccesoRepository accesoRepository;
    
    @Override
    public Acceso create(Acceso cat) {
        return accesoRepository.save(cat);
    }

    @Override
    public Acceso update(Acceso cat) {
        return accesoRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        accesoRepository.deleteById(id);
    }

    @Override
    public Optional<Acceso> read(Long id) {
        return accesoRepository.findById(id);
    }

    @Override
    public List<Acceso> readAll() {
        return accesoRepository.findAll();
    }

}
