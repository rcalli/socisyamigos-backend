
package pe.com.edu.socisyamigos.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import pe.com.edu.socisyamigos.entity.Detalle_PPP;
import pe.com.edu.socisyamigos.entity.Doc;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface DocService {
    public Doc create(Doc cat);
    public Doc update(Doc cat);
    public void delete(Long id);
    public Optional<Doc> read(Long id);
    public List<Doc> readAll();


    public Resource loadFileAsResource(String fileName);

    public Doc getById(Long id);

    public Doc saveFile(MultipartFile file, Long detalleId, Detalle_PPP detallePPP) throws IOException;

}
