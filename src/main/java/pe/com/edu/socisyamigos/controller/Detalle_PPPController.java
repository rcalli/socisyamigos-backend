
package pe.com.edu.socisyamigos.controller;

import java.util.*;

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
import pe.com.edu.socisyamigos.entity.*;
import pe.com.edu.socisyamigos.repository.Detalle_PPPRepository;
import pe.com.edu.socisyamigos.repository.EstudianteRepository;
import pe.com.edu.socisyamigos.repository.UsuarioRepository;
import pe.com.edu.socisyamigos.service.Detalle_PPPService;

@RestController
@RequestMapping("/api/detalle_ppps")
@CrossOrigin(origins = "http://localhost:4200")
public class Detalle_PPPController {
    
    @Autowired
    private Detalle_PPPService detallePppService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private Detalle_PPPRepository detallePPPRepository;

    @PreAuthorize("hasRole('ADMIN') or hasRole('ESTUDIANTE')")
    @GetMapping
    public ResponseEntity<List<Detalle_PPP>> readAll() {
        try {
            List<Detalle_PPP> detalle_ppp = detallePppService.readAll();
            if(detalle_ppp.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(detalle_ppp, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('ESTUDIANTE')")
    @PostMapping
    public ResponseEntity<Detalle_PPP> crear(@Valid @RequestBody Detalle_PPP cat) {
        try {
            Detalle_PPP c = detallePppService.create(cat);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('ESTUDIANTE')")
    @GetMapping("/{id}")
    public ResponseEntity<Detalle_PPP> getDetalle_PPPId(@PathVariable("id") Long id) {
        try {
            Detalle_PPP c = detallePppService.read(id).get();
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('ESTUDIANTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Detalle_PPP> delDetalle_PPP(@PathVariable("id") Long id) {
        try {
            detallePppService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('ESTUDIANTE')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDetalle_PPP(@PathVariable("id") Long id, @Valid @RequestBody Detalle_PPP cat) {
        Optional<Detalle_PPP> c = detallePppService.read(id);
        if(!c.isEmpty()) {
            return new ResponseEntity<>(detallePppService.update(cat), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('ESTUDIANTE')")
    @GetMapping("/ppp/usuario/{userId}")
    public ResponseEntity<?> getPPPDetailsByUserId(@PathVariable Long userId) {
        try {
            // Verificar si el usuario existe
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(userId);
            if (!usuarioOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }

            Usuario usuario = usuarioOptional.get();
            Persona personaUsuario = usuario.getPersona();

            // Buscar estudiante relacionado con la misma persona
            Optional<Estudiante> estudianteOptional = estudianteRepository.findByPersona(personaUsuario);
            if (!estudianteOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estudiante no encontrado para el usuario dado");
            }

            Estudiante estudiante = estudianteOptional.get();
            Set<Matricula> matriculas = estudiante.getMatriculas();

            if (matriculas == null || matriculas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron matrículas para el estudiante");
            }

            // Seleccionar la primera matrícula
            Matricula matricula = matriculas.iterator().next();

            Set<PPP> ppps = matricula.getPpps();
            if (ppps == null || ppps.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PPP no encontrada para la matrícula del estudiante");
            }
            // Seleccionar el primer `PPP`
            PPP ppp = ppps.iterator().next();

            // Obtener los detalles de la PPP (Procesos y Requisitos)
            List<Detalle_PPP> detallesPPP = detallePPPRepository.findByPpp(ppp);

            // REEMPLAZANDO LA PERSONA DE DETALLE_PPP POR LA PERSONA DEL USUARIO
            detallesPPP.forEach(detalle -> {
                detalle.setPersona(usuario.getPersona());
            });
            return ResponseEntity.ok(detallesPPP);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener los datos: " + e.getMessage());
        }
    }
}
