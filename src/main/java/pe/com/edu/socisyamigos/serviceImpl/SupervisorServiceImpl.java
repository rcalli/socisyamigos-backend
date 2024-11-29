
package pe.com.edu.socisyamigos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.PPP;
import pe.com.edu.socisyamigos.entity.Supervisor;
import pe.com.edu.socisyamigos.repository.PPPRepository;
import pe.com.edu.socisyamigos.repository.SupervisorRepository;
import pe.com.edu.socisyamigos.service.SupervisorService;

import java.util.List;
import java.util.Optional;

@Service
public class SupervisorServiceImpl implements SupervisorService {
    
    @Autowired
    private SupervisorRepository supervisorRepository;

    @Autowired
    private PPPRepository pppRepository;
    
    @Override
    public Supervisor create(Supervisor cat) {
        return supervisorRepository.save(cat);
    }

    @Override
    public Supervisor update(Supervisor cat) {
        return supervisorRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        supervisorRepository.deleteById(id);
    }

    @Override
    public Optional<Supervisor> read(Long id) {
        return supervisorRepository.findById(id);
    }

    @Override
    public List<Supervisor> readAll() {
        return supervisorRepository.findAll();
    }

    @Override
    public List<Supervisor> findByCarreraId(Long carreraId) {
        return supervisorRepository.findByCarreraId(carreraId);
    }

    @Override
    public void assignSupervisorToPPP(Long idPPP, Long idSupervisor) {
        // Verificar que el supervisor existe
        Supervisor supervisor = supervisorRepository.findById(idSupervisor)
                .orElseThrow(() -> new RuntimeException("Supervisor no encontrado con ID: " + idSupervisor));

        // Verificar que el PPP existe
        PPP ppp = pppRepository.findById(idPPP)
                .orElseThrow(() -> new RuntimeException("PPP no encontrado con ID: " + idPPP));

        // Asignar el supervisor al PPP
        ppp.setSupervisor(supervisor);

        ppp.setEstado(5);
        // Guardar los cambios en la base de datos
        pppRepository.save(ppp);
    }
}
