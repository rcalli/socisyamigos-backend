
package pe.com.edu.socisyamigos.service;

import pe.com.edu.socisyamigos.entity.PPP;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PPPService {
    public PPP create(PPP cat);
    public PPP update(PPP cat);
    public void delete(Long id);
    public Optional<PPP> read(Long id);
    public List<PPP> readAll();
    public List<Map<String, Object>> findFilteredPPPsByProcessName(String nombreProceso);
    PPP findById(Long idPPP);
}
