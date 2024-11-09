
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
import pe.com.edu.socisyamigos.entity.Usuario_rol;
import pe.com.edu.socisyamigos.service.Usuario_rolService;

@RestController
@RequestMapping("/api/usuario_roles")
@CrossOrigin(origins = "http://localhost:4200")
public class Usuario_rolController {
    
    @Autowired
    private Usuario_rolService usuarioRolService;
    
    @GetMapping
    public ResponseEntity<List<Usuario_rol>> readAll() {
        try {
            List<Usuario_rol> usuario_rol = usuarioRolService.readAll();
            if(usuario_rol.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(usuario_rol, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Usuario_rol> crear(@Valid @RequestBody Usuario_rol cat) {
        try {
            Usuario_rol c = usuarioRolService.create(cat);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario_rol> getUsuario_rolId(@PathVariable("id") Long id) {
        try {
            Usuario_rol c = usuarioRolService.read(id).get();
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario_rol> delUsuario_rol(@PathVariable("id") Long id) {
        try {
            usuarioRolService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario_rol(@PathVariable("id") Long id, @Valid @RequestBody Usuario_rol cat) {
        Optional<Usuario_rol> c = usuarioRolService.read(id);
        if(!c.isEmpty()) {
            return new ResponseEntity<>(usuarioRolService.update(cat), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
