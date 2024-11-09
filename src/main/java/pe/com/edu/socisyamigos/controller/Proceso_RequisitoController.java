
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
import pe.com.edu.socisyamigos.entity.Proceso_Requisito;
import pe.com.edu.socisyamigos.service.Proceso_RequisitoService;

@RestController
@RequestMapping("/api/proceso_requisitos")
@CrossOrigin(origins = "http://localhost:4200")
public class Proceso_RequisitoController {
    
    @Autowired
    private Proceso_RequisitoService procesoRequisitoService;
    
    @GetMapping
    public ResponseEntity<List<Proceso_Requisito>> readAll() {
        try {
            List<Proceso_Requisito> proceso_requisito = procesoRequisitoService.readAll();
            if(proceso_requisito.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(proceso_requisito, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Proceso_Requisito> crear(@Valid @RequestBody Proceso_Requisito cat) {
        try {
            Proceso_Requisito c = procesoRequisitoService.create(cat);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proceso_Requisito> getProceso_RequisitoId(@PathVariable("id") Long id) {
        try {
            Proceso_Requisito c = procesoRequisitoService.read(id).get();
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Proceso_Requisito> delProceso_Requisito(@PathVariable("id") Long id) {
        try {
            procesoRequisitoService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProceso_Requisito(@PathVariable("id") Long id, @Valid @RequestBody Proceso_Requisito cat) {
        Optional<Proceso_Requisito> c = procesoRequisitoService.read(id);
        if(!c.isEmpty()) {
            return new ResponseEntity<>(procesoRequisitoService.update(cat), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
