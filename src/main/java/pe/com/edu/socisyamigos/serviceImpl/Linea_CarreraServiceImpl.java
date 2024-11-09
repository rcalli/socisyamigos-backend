
package pe.com.edu.socisyamigos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.Linea_Carrera;
import pe.com.edu.socisyamigos.repository.Linea_CarreraRepository;
import pe.com.edu.socisyamigos.service.Linea_CarreraService;

import java.util.List;
import java.util.Optional;

@Service
public class Linea_CarreraServiceImpl implements Linea_CarreraService {
    
    @Autowired
    private Linea_CarreraRepository lineaCarreraRepository;
    
    @Override
    public Linea_Carrera create(Linea_Carrera cat) {
        return lineaCarreraRepository.save(cat);
    }

    @Override
    public Linea_Carrera update(Linea_Carrera cat) {
        return lineaCarreraRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        lineaCarreraRepository.deleteById(id);
    }

    @Override
    public Optional<Linea_Carrera> read(Long id) {
        return lineaCarreraRepository.findById(id);
    }

    @Override
    public List<Linea_Carrera> readAll() {
        return lineaCarreraRepository.findAll();
    }

}
