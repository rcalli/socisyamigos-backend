
package pe.com.edu.socisyamigos.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import pe.com.edu.socisyamigos.dto.CrearPPPDto;
import pe.com.edu.socisyamigos.dto.EstadoPPPDto;
import pe.com.edu.socisyamigos.entity.PPP;
import pe.com.edu.socisyamigos.repository.PPPRepository;
import pe.com.edu.socisyamigos.service.PPPService;

@RestController
@RequestMapping("/api/ppps")
@CrossOrigin(origins = "http://localhost:4200")
public class PPPController {
    
    @Autowired
    private PPPService pppService;

    @Autowired
    private PPPRepository pppRepository;

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
    @PreAuthorize("hasRole('ADMIN') or hasRole('COORDINADOR')")
    @GetMapping("/estado")
    public ResponseEntity<?> getPPPsByEstado(@RequestParam List<Integer> estados) {
        try {
            if (estados == null || estados.isEmpty()) {
                return ResponseEntity.badRequest().body("Debe proporcionar al menos un estado.");
            }

            List<PPP> ppps = pppRepository.findByEstadoIn(estados);
            return ResponseEntity.ok(ppps);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener PPP: " + e.getMessage());
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/filtered/{nombreProceso}")
    public List<Map<String, Object>> getFilteredPPPs(@PathVariable String nombreProceso) {
        return pppService.findFilteredPPPsByProcessName(nombreProceso);
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('COORDINADOR')")
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
    public ResponseEntity<Map<String, String>> aceptarPPP(@PathVariable Long id, @RequestBody EstadoPPPDto request) {
        pppService.aceptarPPP(id, request.getEstadoPPP(), request.getEstadoDetallePPP(), request.getProcesoNombre());
        Map<String, String> response = new HashMap<>();
        response.put("message", "PPP y detalles actualizados correctamente.");
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/rechazar")
    public ResponseEntity<Map<String, String>> rechazarPPP(@PathVariable Long id, @RequestBody EstadoPPPDto request) {
        pppService.rechazarPPP(id, request.getEstadoPPP(), request.getEstadoDetallePPP(), request.getProcesoNombre());
        Map<String, String> response = new HashMap<>();
        response.put("message", "PPP rechazada y detalles actualizados correctamente.");
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ESTUDIANTE')")
    @PostMapping("/crear-ppp")
    public ResponseEntity<?> createPPP(@RequestBody CrearPPPDto request) {
        try {
            String mensaje = pppService.createPPP(request); // Llama al servicio
            Map<String, String> response = new HashMap<>();
            response.put("message", mensaje); // Devuelve el mensaje de éxito
            return ResponseEntity.ok(response); // Respuesta con estado 200
        } catch (RuntimeException e) {
            // Manejo de excepciones lanzadas por el servicio
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse); // Respuesta con estado 400
        } catch (Exception e) {
            // Manejo de otros errores
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error inesperado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse); // Respuesta con estado 500
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear-detalles/{idPPP}")
    public ResponseEntity<?> crearDetallesPPP(@PathVariable Long idPPP) {
        try {
            pppService.crearDetallesPPP(idPPP);
            return ResponseEntity.ok(Map.of("message", "Detalles de PPP creados exitosamente."));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al crear detalles de PPP: " + e.getMessage()));
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/rechazar-solicitud")
    public ResponseEntity<Map<String, String>> rechazarPPP(@PathVariable Long id) {
        try {
            Optional<PPP> pppOptional = pppService.read(id);
            if (pppOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "PPP no encontrada."));
            }

            PPP ppp = pppOptional.get();
            if (ppp.getEstado() != 0) { // Verifica si está en estado pendiente
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "La PPP no está en estado pendiente y no puede ser rechazada."));
            }

            // Cambiar el estado de la PPP a rechazado
            ppp.setEstado(2);
            pppService.update(ppp);

            return ResponseEntity.ok(Map.of("message", "PPP rechazada correctamente."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al rechazar la PPP: " + e.getMessage()));
        }
    }

}
