
package pe.com.edu.socisyamigos.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.edu.socisyamigos.entity.Doc;
import pe.com.edu.socisyamigos.repository.DocRepository;
import pe.com.edu.socisyamigos.service.DocService;

import java.util.List;
import java.util.Optional;

@Service
public class DocServiceImpl implements DocService {
    
    @Autowired
    private DocRepository docRepository;
    
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

}
