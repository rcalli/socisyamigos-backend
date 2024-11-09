
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
import pe.com.edu.socisyamigos.entity.Supervisor;
import pe.com.edu.socisyamigos.service.SupervisorService;

@RestController
@RequestMapping("/api/supervisores")
@CrossOrigin(origins = "http://localhost:4200")
public class SupervisorController {
    
    @Autowired
    private SupervisorService supervisorService;
    
    @GetMapping
    public ResponseEntity<List<Supervisor>> readAll() {
        try {
            List<Supervisor> supervisor = supervisorService.readAll();
            if(supervisor.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(supervisor, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Supervisor> crear(@Valid @RequestBody Supervisor cat) {
        try {
            Supervisor c = supervisorService.create(cat);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supervisor> getSupervisorId(@PathVariable("id") Long id) {
        try {
            Supervisor c = supervisorService.read(id).get();
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Supervisor> delSupervisor(@PathVariable("id") Long id) {
        try {
            supervisorService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSupervisor(@PathVariable("id") Long id, @Valid @RequestBody Supervisor cat) {
        Optional<Supervisor> c = supervisorService.read(id);
        if(!c.isEmpty()) {
            return new ResponseEntity<>(supervisorService.update(cat), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
