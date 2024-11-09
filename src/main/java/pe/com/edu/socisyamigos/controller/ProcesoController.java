
package pe.com.edu.socisyamigos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import pe.com.edu.socisyamigos.entity.Proceso;
import pe.com.edu.socisyamigos.service.ProcesoService;

@RestController
@RequestMapping("/api/procesos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProcesoController {
    
    @Autowired
    private ProcesoService procesoService;
    
    @GetMapping
    public ResponseEntity<List<Proceso>> readAll() {
        try {
            List<Proceso> proceso = procesoService.readAll();
            if(proceso.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(proceso, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Proceso> crear(@Valid @RequestBody Proceso cat) {
        try {
            Proceso c = procesoService.create(cat);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proceso> getProcesoId(@PathVariable("id") Long id) {
        try {
            Proceso c = procesoService.read(id).get();
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Proceso> delProceso(@PathVariable("id") Long id) {
        try {
            procesoService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProceso(@PathVariable("id") Long id, @Valid @RequestBody Proceso cat) {
        Optional<Proceso> c = procesoService.read(id);
        if(!c.isEmpty()) {
            return new ResponseEntity<>(procesoService.update(cat), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
