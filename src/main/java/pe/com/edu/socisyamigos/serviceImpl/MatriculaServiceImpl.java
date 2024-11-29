
package pe.com.edu.socisyamigos.serviceImpl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.Matricula;
import pe.com.edu.socisyamigos.entity.Usuario;
import pe.com.edu.socisyamigos.repository.MatriculaRepository;
import pe.com.edu.socisyamigos.repository.UsuarioRepository;
import pe.com.edu.socisyamigos.service.MatriculaService;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaServiceImpl implements MatriculaService {
    
    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Override
    public Matricula create(Matricula cat) {
        return matriculaRepository.save(cat);
    }

    @Override
    public Matricula update(Matricula cat) {
        return matriculaRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        matriculaRepository.deleteById(id);
    }

    @Override
    public Optional<Matricula> read(Long id) {
        return matriculaRepository.findById(id);
    }

    @Override
    public List<Matricula> readAll() {
        return matriculaRepository.findAll();
    }

    @Override
    public List<Matricula> getMatriculaByUsuarioId(Long idUsuario) {
        // Obtener el usuario
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + idUsuario));

        // Obtener el idPersona del usuario
        Long idPersona = usuario.getPersona().getIdpersona();

        // Buscar matrículas donde el idPersona del estudiante coincida
        return matriculaRepository.findByPersonaId(idPersona);
    }
}
