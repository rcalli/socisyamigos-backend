package pe.com.edu.socisyamigos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pe.com.edu.socisyamigos.service.EstudianteService;
import pe.com.edu.socisyamigos.service.PersonaService;
import pe.com.edu.socisyamigos.serviceImpl.EstudianteServiceImpl;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {

    private final EstudianteServiceImpl estudianteServiceImpl;

    public ExcelController(EstudianteServiceImpl estudianteServiceImpl) {
        this.estudianteServiceImpl = estudianteServiceImpl;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> cargarArchivoExcel(@RequestParam("file") MultipartFile file) {
        try {
            estudianteServiceImpl.cargarDatosDesdeExcel(file);
            return ResponseEntity.ok("Datos cargados con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al cargar datos");
        }
    }
}

