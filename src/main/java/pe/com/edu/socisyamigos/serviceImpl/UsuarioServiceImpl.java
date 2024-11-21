
package pe.com.edu.socisyamigos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.Usuario;
import pe.com.edu.socisyamigos.repository.UsuarioRepository;
import pe.com.edu.socisyamigos.service.UsuarioService;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Override
    public Usuario create(Usuario cat) {
        return usuarioRepository.save(cat);
    }

    @Override
    public Usuario update(Usuario cat) {
        return usuarioRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Optional<Usuario> read(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public List<Usuario> readAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Long> findIdByUsername(String username) {
        return usuarioRepository.findIdByUsername(username);
    }
}
