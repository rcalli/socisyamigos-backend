
package pe.com.edu.socisyamigos.service;

import pe.com.edu.socisyamigos.entity.Doc;

import java.util.List;
import java.util.Optional;

public interface DocService {
    public Doc create(Doc cat);
    public Doc update(Doc cat);
    public void delete(Long id);
    public Optional<Doc> read(Long id);
    public List<Doc> readAll();
}
