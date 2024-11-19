
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
import pe.com.edu.socisyamigos.entity.Plan_Evaluacion;
import pe.com.edu.socisyamigos.service.Plan_EvaluacionService;

@RestController
@RequestMapping("/api/plan_evaluaciones")
@CrossOrigin(origins = "http://localhost:4200")
public class Plan_EvaluacionController {
    
    @Autowired
    private Plan_EvaluacionService planEvaluacionService;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Plan_Evaluacion>> readAll() {
        try {
            List<Plan_Evaluacion> plan_evaluacion = planEvaluacionService.readAll();
            if(plan_evaluacion.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(plan_evaluacion, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Plan_Evaluacion> crear(@Valid @RequestBody Plan_Evaluacion cat) {
        try {
            Plan_Evaluacion c = planEvaluacionService.create(cat);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Plan_Evaluacion> getPlan_EvaluacionId(@PathVariable("id") Long id) {
        try {
            Plan_Evaluacion c = planEvaluacionService.read(id).get();
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Plan_Evaluacion> delPlan_Evaluacion(@PathVariable("id") Long id) {
        try {
            planEvaluacionService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlan_Evaluacion(@PathVariable("id") Long id, @Valid @RequestBody Plan_Evaluacion cat) {
        Optional<Plan_Evaluacion> c = planEvaluacionService.read(id);
        if(!c.isEmpty()) {
            return new ResponseEntity<>(planEvaluacionService.update(cat), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
