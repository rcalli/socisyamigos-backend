
package pe.com.edu.socisyamigos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.Acceso_rol;
import pe.com.edu.socisyamigos.repository.Acceso_rolRepository;
import pe.com.edu.socisyamigos.service.Acceso_rolService;

import java.util.List;
import java.util.Optional;

@Service
public class Acceso_rolServiceImpl implements Acceso_rolService {
    
    @Autowired
    private Acceso_rolRepository accesoRolRepository;
    
    @Override
    public Acceso_rol create(Acceso_rol cat) {
        return accesoRolRepository.save(cat);
    }

    @Override
    public Acceso_rol update(Acceso_rol cat) {
        return accesoRolRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        accesoRolRepository.deleteById(id);
    }

    @Override
    public Optional<Acceso_rol> read(Long id) {
        return accesoRolRepository.findById(id);
    }

    @Override
    public List<Acceso_rol> readAll() {
        return accesoRolRepository.findAll();
    }

}
