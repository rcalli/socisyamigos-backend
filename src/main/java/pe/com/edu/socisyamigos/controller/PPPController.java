
package pe.com.edu.socisyamigos.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import pe.com.edu.socisyamigos.entity.PPP;
import pe.com.edu.socisyamigos.service.PPPService;

@RestController
@RequestMapping("/api/ppps")
@CrossOrigin(origins = "http://localhost:4200")
public class PPPController {
    
    @Autowired
    private PPPService pppService;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<PPP>> readAll() {
        try {
            List<PPP> ppp = pppService.readAll();
            if(ppp.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(ppp, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PPP> crear(@Valid @RequestBody PPP cat) {
        try {
            PPP c = pppService.create(cat);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<PPP> getPPPId(@PathVariable("id") Long id) {
        try {
            PPP c = pppService.read(id).get();
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<PPP> delPPP(@PathVariable("id") Long id) {
        try {
            pppService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePPP(@PathVariable("id") Long id, @Valid @RequestBody PPP cat) {
        Optional<PPP> c = pppService.read(id);
        if(!c.isEmpty()) {
            return new ResponseEntity<>(pppService.update(cat), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/filtered/{nombreProceso}")
    public List<Map<String, Object>> getFilteredPPPs(@PathVariable String nombreProceso) {
        return pppService.findFilteredPPPsByProcessName(nombreProceso);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/detalle/{idPPP}")
    public ResponseEntity<?> getEstudianteDetalle(@PathVariable Long idPPP) {
        try {
            var ppp = pppService.findById(idPPP);
            var estudiante = ppp.getMatricula().getEstudiante();
            var matricula = ppp.getMatricula();
            var response = Map.of(
                    "nombre", estudiante.getPersona().getNombre(),
                    "dni", estudiante.getPersona().getDni(),
                    "programa", matricula.getPlan_carrera().getCarrera().getNombre(),
                    "plan", matricula.getPlan_carrera().getPlan().getNombre(),
                    "lineaTrabajo", ppp.getLinea_carrera().getNombre(),
                    "direccion", estudiante.getPersona().getDireccion(),
                    "codigo", estudiante.getCodigo(),
                    "correo", estudiante.getPersona().getCorreo(),
                    "telefono", estudiante.getPersona().getTelefono()
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró información para el idPPP: " + idPPP);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/aceptar")
    public ResponseEntity<String> aceptarPPP(@PathVariable Long id) {
        pppService.aceptarPPP(id);
        return ResponseEntity.ok("PPP aceptada y detalles actualizados.");
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/rechazar")
    public ResponseEntity<String> rechazarPPP(@PathVariable Long id) {
        pppService.rechazarPPP(id);
        return ResponseEntity.ok("PPP rechazada y detalles actualizados.");
    }

}
