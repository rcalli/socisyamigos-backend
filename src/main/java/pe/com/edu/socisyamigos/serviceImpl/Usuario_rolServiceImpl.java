
package pe.com.edu.socisyamigos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.Usuario_rol;
import pe.com.edu.socisyamigos.repository.Usuario_rolRepository;
import pe.com.edu.socisyamigos.service.Usuario_rolService;

import java.util.List;
import java.util.Optional;

@Service
public class Usuario_rolServiceImpl implements Usuario_rolService {
    
    @Autowired
    private Usuario_rolRepository usuarioRolRepository;
    
    @Override
    public Usuario_rol create(Usuario_rol cat) {
        return usuarioRolRepository.save(cat);
    }

    @Override
    public Usuario_rol update(Usuario_rol cat) {
        return usuarioRolRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        usuarioRolRepository.deleteById(id);
    }

    @Override
    public Optional<Usuario_rol> read(Long id) {
        return usuarioRolRepository.findById(id);
    }

    @Override
    public List<Usuario_rol> readAll() {
        return usuarioRolRepository.findAll();
    }

}
