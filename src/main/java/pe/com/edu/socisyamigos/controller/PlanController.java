
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
import pe.com.edu.socisyamigos.entity.Plan;
import pe.com.edu.socisyamigos.service.PlanService;

@RestController
@RequestMapping("/api/planes")
@CrossOrigin(origins = "http://localhost:4200")
public class PlanController {
    
    @Autowired
    private PlanService planService;
    
    @GetMapping
    public ResponseEntity<List<Plan>> readAll() {
        try {
            List<Plan> plan = planService.readAll();
            if(plan.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(plan, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Plan> crear(@Valid @RequestBody Plan cat) {
        try {
            Plan c = planService.create(cat);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plan> getPlanId(@PathVariable("id") Long id) {
        try {
            Plan c = planService.read(id).get();
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Plan> delPlan(@PathVariable("id") Long id) {
        try {
            planService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlan(@PathVariable("id") Long id, @Valid @RequestBody Plan cat) {
        Optional<Plan> c = planService.read(id);
        if(!c.isEmpty()) {
            return new ResponseEntity<>(planService.update(cat), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
