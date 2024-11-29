
package pe.com.edu.socisyamigos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.Empresa;
import pe.com.edu.socisyamigos.repository.EmpresaRepository;
import pe.com.edu.socisyamigos.service.EmpresaService;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaServiceImpl implements EmpresaService {
    
    @Autowired
    private EmpresaRepository empresaRepository;
    
    @Override
    public Empresa create(Empresa cat) {
        return empresaRepository.save(cat);
    }

    @Override
    public Empresa update(Empresa cat) {
        return empresaRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        empresaRepository.deleteById(id);
    }

    @Override
    public Optional<Empresa> read(Long id) {
        return empresaRepository.findById(id);
    }

    @Override
    public List<Empresa> readAll() {
        return empresaRepository.findAll();
    }

}
