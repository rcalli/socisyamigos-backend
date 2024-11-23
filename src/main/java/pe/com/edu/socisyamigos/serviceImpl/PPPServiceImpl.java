
package pe.com.edu.socisyamigos.serviceImpl;

import jakarta.persistence.EntityNotFoundException;
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
    @Autowired
    private Detalle_PPPRepository detalle_PPPRepository;

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
    @Override
    public PPP findById(Long idPPP) {
        return pppRepository.findById(idPPP)
                .orElseThrow(() -> new RuntimeException("PPP no encontrado con id: " + idPPP));
    }
    @Override
    public void aceptarPPP(Long idPPP) {
        // Cambiar estado de la PPP a 3
        PPP ppp = pppRepository.findById(idPPP)
                .orElseThrow(() -> new EntityNotFoundException("PPP no encontrada con id: " + idPPP));
        ppp.setEstado(3);
        pppRepository.save(ppp);

        // Cambiar estado de los Detalle_PPP a 2
        List<Detalle_PPP> detalles = detalle_PPPRepository.findByPpp_IdAndProcesoNombre(idPPP, "Docs Inicio");
        for (Detalle_PPP detalle : detalles) {
            detalle.setEstado(2);
        }
        detalle_PPPRepository.saveAll(detalles);
    }
    @Override
    public void rechazarPPP(Long idPPP) {
        // Cambiar estado de la PPP a 4
        PPP ppp = pppRepository.findById(idPPP)
                .orElseThrow(() -> new EntityNotFoundException("PPP no encontrada con id: " + idPPP));
        ppp.setEstado(4);
        pppRepository.save(ppp);

        // Cambiar estado de los Detalle_PPP a 3
        List<Detalle_PPP> detalles = detalle_PPPRepository.findByPpp_IdAndProcesoNombre(idPPP,"Docs Inicio");
        for (Detalle_PPP detalle : detalles) {
            detalle.setEstado(3);
        }
        detalle_PPPRepository.saveAll(detalles);
    }
}
