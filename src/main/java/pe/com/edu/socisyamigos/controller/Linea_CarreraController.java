
package pe.com.edu.socisyamigos.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import pe.com.edu.socisyamigos.entity.Carrera;
import pe.com.edu.socisyamigos.entity.Linea_Carrera;
import pe.com.edu.socisyamigos.service.CarreraService;
import pe.com.edu.socisyamigos.service.Linea_CarreraService;

@RestController
@RequestMapping("/api/linea_carreras")
@CrossOrigin(origins = "http://localhost:4200")
public class Linea_CarreraController {
    
    @Autowired
    private Linea_CarreraService lineaCarreraService;

    @Autowired
    private CarreraService carreraService;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Linea_Carrera>> readAll() {
        try {
            List<Linea_Carrera> linea_carrera = lineaCarreraService.readAll();
            if(linea_carrera.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(linea_carrera, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Linea_Carrera> crear(@Valid @RequestBody Linea_Carrera cat) {
        try {
            Linea_Carrera c = lineaCarreraService.create(cat);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Linea_Carrera> getLinea_CarreraId(@PathVariable("id") Long id) {
        try {
            Linea_Carrera c = lineaCarreraService.read(id).get();
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Linea_Carrera> delLinea_Carrera(@PathVariable("id") Long id) {
        try {
            lineaCarreraService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateLinea_Carrera(@PathVariable("id") Long id, @Valid @RequestBody Linea_Carrera cat) {
        Optional<Linea_Carrera> c = lineaCarreraService.read(id);
        if(!c.isEmpty()) {
            return new ResponseEntity<>(lineaCarreraService.update(cat), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<Linea_Carrera> partialUpdateLineaCarrera(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        Optional<Linea_Carrera> optionalLineaCarrera = lineaCarreraService.read(id);

        if (optionalLineaCarrera.isPresent()) {
            Linea_Carrera lineaCarrera = optionalLineaCarrera.get();

            updates.forEach((key, value) -> {
                if ("carrera".equals(key)) {
                    // Handle nested Carrera object
                    Map<String, Object> carreraData = (Map<String, Object>) value;
                    if (carreraData.containsKey("id")) {
                        Long carreraId = Long.valueOf(carreraData.get("id").toString());
                        Optional<Carrera> carrera = carreraService.read(carreraId);
                        if (carrera.isPresent()) {
                            lineaCarrera.setCarrera(carrera.get());
                        } else {
                            throw new IllegalArgumentException("Invalid Carrera ID: " + carreraId);
                        }
                    }
                } else {
                    // Handle other fields dynamically
                    Field field = ReflectionUtils.findField(Linea_Carrera.class, key);
                    if (field != null) {
                        field.setAccessible(true);
                        ReflectionUtils.setField(field, lineaCarrera, value);
                    }
                }
            });

            Linea_Carrera updatedLineaCarrera = lineaCarreraService.update(lineaCarrera);
            return ResponseEntity.ok(updatedLineaCarrera);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/matricula/{idMatricula}")
    public ResponseEntity<?> getLineasCarreraByIdMatricula(@PathVariable Long idMatricula) {
        try {
            List<Linea_Carrera> lineasCarrera = lineaCarreraService.getLineasCarreraByIdMatricula(idMatricula);
            if (lineasCarrera.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontraron líneas de carrera para la matrícula con ID: " + idMatricula);
            }
            return ResponseEntity.ok(lineasCarrera);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las líneas de carrera: " + e.getMessage());
        }
    }

}
