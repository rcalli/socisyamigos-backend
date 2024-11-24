
package pe.com.edu.socisyamigos.serviceImpl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.dto.CrearPPPDto;
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
    @Autowired
    private MatriculaRepository matriculaRepository;
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private Linea_CarreraRepository linea_CarreraRepository;

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
    public void aceptarPPP(Long idPPP, int estadoPPP, int estadoDetallePPP, String procesoNombre) {
        // Cambiar estado de la PPP a 3
        PPP ppp = pppRepository.findById(idPPP)
                .orElseThrow(() -> new EntityNotFoundException("PPP no encontrada con id: " + idPPP));
        ppp.setEstado(estadoPPP);
        pppRepository.save(ppp);

        // Cambiar estado de los Detalle_PPP filtrados por procesoNombre
        List<Detalle_PPP> detalles = detalle_PPPRepository.findByPpp_IdAndProcesoNombre(idPPP, procesoNombre);
        for (Detalle_PPP detalle : detalles) {
            detalle.setEstado(estadoDetallePPP);
        }
        detalle_PPPRepository.saveAll(detalles);
    }
    @Override
    public void rechazarPPP(Long idPPP, int estadoPPP, int estadoDetallePPP, String procesoNombre) {
        // Cambiar estado de la PPP a 4
        PPP ppp = pppRepository.findById(idPPP)
                .orElseThrow(() -> new EntityNotFoundException("PPP no encontrada con id: " + idPPP));
        ppp.setEstado(estadoPPP);
        pppRepository.save(ppp);

        // Cambiar estado de los Detalle_PPP filtrados por procesoNombre
        List<Detalle_PPP> detalles = detalle_PPPRepository.findByPpp_IdAndProcesoNombre(idPPP, procesoNombre);
        for (Detalle_PPP detalle : detalles) {
            detalle.setEstado(estadoDetallePPP);
        }
        detalle_PPPRepository.saveAll(detalles);
    }
    @Override
    public String createPPP(CrearPPPDto request) {
        // Verificar si ya existe un registro para la matrícula
        boolean existePPP = pppRepository.existsByMatriculaId(request.getIdMatricula());
        if (existePPP) {
            throw new RuntimeException("Ya existe un registro de PPP para la matrícula proporcionada.");
        }

        // Verificar que la matrícula exista
        Matricula matricula = matriculaRepository.findById(request.getIdMatricula())
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada con ID: " + request.getIdMatricula()));

        // Verificar que la empresa exista
        Empresa empresa = empresaRepository.findById(request.getIdEmpresa())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + request.getIdEmpresa()));

        // Verificar que la línea de carrera exista
        Linea_Carrera lineaCarrera = null;
        if (request.getIdLineaCarrera() != null) {
            lineaCarrera = linea_CarreraRepository.findById(request.getIdLineaCarrera())
                    .orElseThrow(() -> new RuntimeException("Línea de carrera no encontrada con ID: " + request.getIdLineaCarrera()));
        }

        // Crear un nuevo registro de PPP
        PPP ppp = new PPP();
        ppp.setMatricula(matricula);
        ppp.setEmpresa(empresa);
        ppp.setLinea_carrera(lineaCarrera); // Puede ser null
        ppp.setEstado(0); // Estado inicial
        ppp.setFechaInicio(null);
        ppp.setFechaFin(null);
        ppp.setHoras(0);
        ppp.setPromedio(null);

        pppRepository.save(ppp);
        return "PPP creado exitosamente.";
    }
}
