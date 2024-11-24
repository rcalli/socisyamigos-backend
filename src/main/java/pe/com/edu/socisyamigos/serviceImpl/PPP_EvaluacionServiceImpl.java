
package pe.com.edu.socisyamigos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.Evaluacion;
import pe.com.edu.socisyamigos.entity.PPP;
import pe.com.edu.socisyamigos.entity.PPP_Evaluacion;
import pe.com.edu.socisyamigos.repository.EvaluacionRepository;
import pe.com.edu.socisyamigos.repository.PPPRepository;
import pe.com.edu.socisyamigos.repository.PPP_EvaluacionRepository;
import pe.com.edu.socisyamigos.service.PPP_EvaluacionService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PPP_EvaluacionServiceImpl implements PPP_EvaluacionService {
    
    @Autowired
    private PPP_EvaluacionRepository pppEvaluacionRepository;
    @Autowired
    private PPPRepository pppRepository;
    @Autowired
    private EvaluacionRepository evaluacionRepository;
    
    @Override
    public PPP_Evaluacion create(PPP_Evaluacion cat) {
        return pppEvaluacionRepository.save(cat);
    }

    @Override
    public PPP_Evaluacion update(PPP_Evaluacion cat) {
        return pppEvaluacionRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        pppEvaluacionRepository.deleteById(id);
    }

    @Override
    public Optional<PPP_Evaluacion> read(Long id) {
        return pppEvaluacionRepository.findById(id);
    }

    @Override
    public List<PPP_Evaluacion> readAll() {
        return pppEvaluacionRepository.findAll();
    }
    @Override
    public void crearPPPEvaluacion(Long idPPP, Long idEvaluacion) {
        // Verificar si ya existe un registro con el mismo idPPP e idEvaluacion
        boolean existe = pppEvaluacionRepository.existsByPppIdAndEvaluacionId(idPPP, idEvaluacion);
        if (existe) {
            throw new RuntimeException("Ya existe un registro para este PPP y Evaluación.");
        }

        // Verificar que el PPP existe
        PPP ppp = pppRepository.findById(idPPP)
                .orElseThrow(() -> new RuntimeException("PPP no encontrado con ID: " + idPPP));

        // Verificar que la Evaluación existe
        Evaluacion evaluacion = evaluacionRepository.findById(idEvaluacion)
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada con ID: " + idEvaluacion));

        // Crear y guardar el nuevo registro
        PPP_Evaluacion pppEvaluacion = new PPP_Evaluacion();
        pppEvaluacion.setPpp(ppp);
        pppEvaluacion.setEvaluacion(evaluacion);
        pppEvaluacion.setFecha_registro(new Date());
        pppEvaluacion.setNota(null);
        pppEvaluacion.setRuta_archivo(null);
        pppEvaluacion.setEvidencia(null);
        pppEvaluacion.setEstado(0); // Estado inicial: Pendiente

        ppp.setEstado(6);
        pppEvaluacionRepository.save(pppEvaluacion);
        pppRepository.save(ppp);
    }
    @Override
    public List<PPP_Evaluacion> obtenerEvaluacionesPorPPP(Long idPPP) {
        return pppEvaluacionRepository.findByPppId(idPPP);
    }
}
