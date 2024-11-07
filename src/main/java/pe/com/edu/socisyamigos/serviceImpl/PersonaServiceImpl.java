package pe.com.edu.socisyamigos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.Persona;
import pe.com.edu.socisyamigos.repository.PersonaRepository;
import pe.com.edu.socisyamigos.service.PersonaService;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository categoriaRepository;
    @Override
    public Persona create(Persona cat) {

        return categoriaRepository.save(cat);
    }

    @Override
    public Persona update(Persona cat) {

        return categoriaRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public Optional<Persona> read(Long id) {

        return categoriaRepository.findById(id);
    }

    @Override
    public List<Persona> readAll() {

        return categoriaRepository.findAll();
    }

}