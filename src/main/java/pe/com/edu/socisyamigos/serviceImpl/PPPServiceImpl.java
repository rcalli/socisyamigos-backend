
package pe.com.edu.socisyamigos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.*;
import pe.com.edu.socisyamigos.repository.*;
import pe.com.edu.socisyamigos.service.PPPService;

import java.util.*;

@Service
public class PPPServiceImpl implements PPPService {

    @Autowired
    private PPPRepository pppRepository;

    @Autowired
    private ProcesoRepository procesoRepository;

    @Override
    public PPP create(PPP cat) {
        return pppRepository.save(cat);
    }

    @Override
    public PPP update(PPP cat) {
        return pppRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        pppRepository.deleteById(id);
    }

    @Override
    public Optional<PPP> read(Long id) {
        return pppRepository.findById(id);
    }

    @Override
    public List<PPP> readAll() {
        return pppRepository.findAll();
    }

    @Override
    public List<Map<String, Object>> findFilteredPPPsByProcessName(String nombreProceso) {
        Optional<Long> optionalIdProceso = procesoRepository.findIdByNombre(nombreProceso);
        if (optionalIdProceso.isEmpty()) {
            throw new IllegalArgumentException("El proceso con nombre '" + nombreProceso + "' no existe.");
        }

        Long idProceso = optionalIdProceso.get();
        return pppRepository.findFilteredPPPsByProcessId(idProceso);
    }
}
