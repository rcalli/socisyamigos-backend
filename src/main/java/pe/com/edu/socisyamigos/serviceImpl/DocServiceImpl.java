
package pe.com.edu.socisyamigos.serviceImpl;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pe.com.edu.socisyamigos.entity.Detalle_PPP;
import pe.com.edu.socisyamigos.entity.Doc;
import pe.com.edu.socisyamigos.repository.Detalle_PPPRepository;
import pe.com.edu.socisyamigos.repository.DocRepository;
import pe.com.edu.socisyamigos.service.DocService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DocServiceImpl implements DocService {


    @Autowired
    private DocRepository docRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(Paths.get(uploadDir));
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el directorio de carga de archivos");
        }
    }

    public Doc saveFile(MultipartFile file, Long detalleId, Detalle_PPP detallePPP) throws IOException {
        // Crear nombre único para el archivo
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uniqueFileName = System.currentTimeMillis() + "_" + fileName;

        // Crear la ruta completa
        Path targetLocation = Paths.get(uploadDir).resolve(uniqueFileName);

        // Guardar el archivo
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        // Crear y guardar el documento en la base de datos
        Doc doc = new Doc();
        doc.setDetalle_ppp(detallePPP);
        doc.setNombre(fileName);
        doc.setRuta_archivo(uniqueFileName);
        doc.setFecha(new Date(System.currentTimeMillis()));
        doc.setEstado(1); // Estado inicial

        return docRepository.save(doc);
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if(resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("Archivo no encontrado: " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("Archivo no encontrado: " + fileName);
        }
    }

    @Override
    public Doc create(Doc cat) {
        return docRepository.save(cat);
    }

    @Override
    public Doc update(Doc cat) {
        return docRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        docRepository.deleteById(id);
    }

    @Override
    public Optional<Doc> read(Long id) {
        return docRepository.findById(id);
    }

    @Override
    public List<Doc> readAll() {
        return docRepository.findAll();
    }

    @Override
    public Doc getById(Long id) {
        Optional<Doc> optionalDoc = docRepository.findById(id);
        if (optionalDoc.isPresent()) {
            return optionalDoc.get();
        } else {
            throw new RuntimeException("Documento no encontrado con id: " + id);
        }
    }



}
