
package pe.com.edu.socisyamigos.controller;

import java.util.List;
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
import pe.com.edu.socisyamigos.entity.Matricula;
import pe.com.edu.socisyamigos.service.MatriculaService;

@RestController
@RequestMapping("/api/matriculas")
@CrossOrigin(origins = "http://localhost:4200")
public class MatriculaController {
    
    @Autowired
    private MatriculaService matriculaService;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Matricula>> readAll() {
        try {
            List<Matricula> matricula = matriculaService.readAll();
            if(matricula.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(matricula, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Matricula> crear(@Valid @RequestBody Matricula cat) {
        try {
            Matricula c = matriculaService.create(cat);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Matricula> getMatriculaId(@PathVariable("id") Long id) {
        try {
            Matricula c = matriculaService.read(id).get();
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Matricula> delMatricula(@PathVariable("id") Long id) {
        try {
            matriculaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMatricula(@PathVariable("id") Long id, @Valid @RequestBody Matricula cat) {
        Optional<Matricula> c = matriculaService.read(id);
        if(!c.isEmpty()) {
            return new ResponseEntity<>(matriculaService.update(cat), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('ESTUDIANTE')")
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> getMatriculaByUsuarioId(@PathVariable Long idUsuario) {
        try {
            List<Matricula> matriculas = matriculaService.getMatriculaByUsuarioId(idUsuario);
            if (matriculas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron matrículas para el usuario con ID: " + idUsuario);
            }
            return ResponseEntity.ok(matriculas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener matrículas: " + e.getMessage());
        }
    }
}
