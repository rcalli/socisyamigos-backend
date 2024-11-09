
package pe.com.edu.socisyamigos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.Matricula;
import pe.com.edu.socisyamigos.repository.MatriculaRepository;
import pe.com.edu.socisyamigos.service.MatriculaService;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaServiceImpl implements MatriculaService {
    
    @Autowired
    private MatriculaRepository matriculaRepository;
    
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

}
