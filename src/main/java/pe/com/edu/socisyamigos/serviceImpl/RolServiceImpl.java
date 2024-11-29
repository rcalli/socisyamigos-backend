
package pe.com.edu.socisyamigos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.Rol;
import pe.com.edu.socisyamigos.repository.RolRepository;
import pe.com.edu.socisyamigos.service.RolService;

import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImpl implements RolService {
    
    @Autowired
    private RolRepository rolRepository;
    
    @Override
    public Rol create(Rol cat) {
        return rolRepository.save(cat);
    }

    @Override
    public Rol update(Rol cat) {
        return rolRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        rolRepository.deleteById(id);
    }

    @Override
    public Optional<Rol> read(Long id) {
        return rolRepository.findById(id);
    }

    @Override
    public List<Rol> readAll() {
        return rolRepository.findAll();
    }

}
