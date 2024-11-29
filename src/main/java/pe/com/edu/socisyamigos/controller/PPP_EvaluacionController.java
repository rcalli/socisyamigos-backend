
package pe.com.edu.socisyamigos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import pe.com.edu.socisyamigos.dto.PPPEvaluacionCronogramaDto;
import pe.com.edu.socisyamigos.entity.PPP_Evaluacion;
import pe.com.edu.socisyamigos.service.PPP_EvaluacionService;

@RestController
@RequestMapping("/api/ppp_evaluaciones")
@CrossOrigin(origins = "http://localhost:4200")
public class PPP_EvaluacionController {
    
    @Autowired
    private PPP_EvaluacionService pppEvaluacionService;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<PPP_Evaluacion>> readAll() {
        try {
            List<PPP_Evaluacion> ppp_evaluacion = pppEvaluacionService.readAll();
            if(ppp_evaluacion.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(ppp_evaluacion, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PPP_Evaluacion> crear(@Valid @RequestBody PPP_Evaluacion cat) {
        try {
            PPP_Evaluacion c = pppEvaluacionService.create(cat);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<PPP_Evaluacion> getPPP_EvaluacionId(@PathVariable("id") Long id) {
        try {
            PPP_Evaluacion c = pppEvaluacionService.read(id).get();
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<PPP_Evaluacion> delPPP_Evaluacion(@PathVariable("id") Long id) {
        try {
            pppEvaluacionService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePPP_Evaluacion(@PathVariable("id") Long id, @Valid @RequestBody PPP_Evaluacion cat) {
        Optional<PPP_Evaluacion> c = pppEvaluacionService.read(id);
        if(!c.isEmpty()) {
            return new ResponseEntity<>(pppEvaluacionService.update(cat), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/generar-cronograma")
    public ResponseEntity<String> crearPPPEvaluacion(@RequestBody PPPEvaluacionCronogramaDto request) {
        try {
            pppEvaluacionService.crearPPPEvaluacion(request.getIdPPP(), request.getIdEvaluacion());
            return ResponseEntity.ok("Cronograma generado correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al generar cronograma: " + e.getMessage());
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/ppp/{idPPP}")
    public ResponseEntity<List<PPP_Evaluacion>> obtenerPPPEvaluaciones(@PathVariable Long idPPP) {
        try {
            List<PPP_Evaluacion> evaluaciones = pppEvaluacionService.obtenerEvaluacionesPorPPP(idPPP);
            return ResponseEntity.ok(evaluaciones);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> getPPPEvaluacionesByUsuario(@PathVariable Long idUsuario) {
        try {
            List<PPP_Evaluacion> evaluaciones = pppEvaluacionService.getPPPEvaluacionesByUsuarioId(idUsuario);
            if (evaluaciones.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(evaluaciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener evaluaciones: " + e.getMessage());
        }
    }
    @PutMapping("/registrar-nota")
    public ResponseEntity<Map<String, String>> registrarNota(@RequestBody Map<String, Object> request) {
        Long idPPPEvaluacion = Long.valueOf(request.get("idPPPEvaluacion").toString());
        Integer nota = Integer.valueOf(request.get("nota").toString());

        pppEvaluacionService.registrarNota(idPPPEvaluacion, nota);

        // Retorna un mensaje en formato JSON
        Map<String, String> response = new HashMap<>();
        response.put("message", "Nota registrada exitosamente.");
        return ResponseEntity.ok(response);
    }
}
