
package pe.com.edu.socisyamigos.service;

import pe.com.edu.socisyamigos.entity.PPP_Evaluacion;

import java.util.List;
import java.util.Optional;

public interface PPP_EvaluacionService {
    public PPP_Evaluacion create(PPP_Evaluacion cat);
    public PPP_Evaluacion update(PPP_Evaluacion cat);
    public void delete(Long id);
    public Optional<PPP_Evaluacion> read(Long id);
    public List<PPP_Evaluacion> readAll();
    public void crearPPPEvaluacion(Long idPPP, Long idEvaluacion);
    public List<PPP_Evaluacion> obtenerEvaluacionesPorPPP(Long idPPP);
    public List<PPP_Evaluacion> getPPPEvaluacionesByUsuarioId(Long idUsuario);
    public PPP_Evaluacion registrarNota(Long idPPPEvaluacion, Integer nota);
}
