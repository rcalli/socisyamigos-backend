
package pe.com.edu.socisyamigos.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;
import pe.com.edu.socisyamigos.entity.Detalle_PPP;
import pe.com.edu.socisyamigos.entity.Doc;
import pe.com.edu.socisyamigos.service.DocService;

@RestController
@RequestMapping("/api/docs")
@CrossOrigin(origins = "http://localhost:4200")
public class DocController {
    
    @Autowired
    private DocService docService;

    @PostMapping("/upload/{detalleId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ESTUDIANTE')")
    public ResponseEntity<Doc> uploadFile(
            @RequestParam("file") MultipartFile file,
            @PathVariable Long detalleId,
            @RequestParam("detallePPP") String detallePPPJson) {
        try {
            // Convert JSON string to Detalle_PPP object
            ObjectMapper objectMapper = new ObjectMapper();
            Detalle_PPP detallePPP = objectMapper.readValue(detallePPPJson, Detalle_PPP.class);

            Doc doc = docService.saveFile(file, detalleId, detallePPP);
            return new ResponseEntity<>(doc, HttpStatus.CREATED);
        } catch (IOException ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/download/{fileName:.+}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ESTUDIANTE')")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = docService.loadFileAsResource(fileName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            // Manejar silenciosamente
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ESTUDIANTE')")
    @GetMapping
    public ResponseEntity<List<Doc>> readAll() {
        try {
            List<Doc> doc = docService.readAll();
            if(doc.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(doc, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('ESTUDIANTE')")
    @PostMapping
    public ResponseEntity<Doc> crear(@Valid @RequestBody Doc cat) {
        try {
            Doc c = docService.create(cat);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('ESTUDIANTE')")
    @GetMapping("/{id}")
    public ResponseEntity<Doc> getDocId(@PathVariable("id") Long id) {
        try {
            Doc c = docService.read(id).get();
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('ESTUDIANTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Doc> delDoc(@PathVariable("id") Long id) {
        try {
            docService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('ESTUDIANTE')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDoc(@PathVariable("id") Long id, @Valid @RequestBody Doc cat) {
        Optional<Doc> c = docService.read(id);
        if(!c.isEmpty()) {
            return new ResponseEntity<>(docService.update(cat), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
