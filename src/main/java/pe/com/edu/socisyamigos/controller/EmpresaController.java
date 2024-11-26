
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
import pe.com.edu.socisyamigos.entity.Empresa;
import pe.com.edu.socisyamigos.service.EmpresaService;

@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpresaController {
    
    @Autowired
    private EmpresaService empresaService;
    @PreAuthorize("hasRole('ADMIN') or hasRole('ESTUDIANTE')")
    @GetMapping
    public ResponseEntity<List<Empresa>> readAll() {
        try {
            List<Empresa> empresa = empresaService.readAll();
            if(empresa.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(empresa, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('ESTUDIANTE')")
    @PostMapping
    public ResponseEntity<Empresa> crear(@Valid @RequestBody Empresa cat) {
        try {
            Empresa c = empresaService.create(cat);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('ESTUDIANTE')")
    @GetMapping("/{id}")
    public ResponseEntity<Empresa> getEmpresaId(@PathVariable("id") Long id) {
        try {
            Empresa c = empresaService.read(id).get();
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('ESTUDIANTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Empresa> delEmpresa(@PathVariable("id") Long id) {
        try {
            empresaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('ESTUDIANTE')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmpresa(@PathVariable("id") Long id, @Valid @RequestBody Empresa cat) {
        Optional<Empresa> c = empresaService.read(id);
        if(!c.isEmpty()) {
            return new ResponseEntity<>(empresaService.update(cat), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
