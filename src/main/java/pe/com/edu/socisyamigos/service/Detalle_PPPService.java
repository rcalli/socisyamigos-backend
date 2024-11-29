
package pe.com.edu.socisyamigos.service;

import pe.com.edu.socisyamigos.entity.Detalle_PPP;

import java.util.List;
import java.util.Optional;

public interface Detalle_PPPService {
    public Detalle_PPP create(Detalle_PPP cat);
    public Detalle_PPP update(Detalle_PPP cat);
    public void delete(Long id);
    public Optional<Detalle_PPP> read(Long id);
    public List<Detalle_PPP> readAll();
}
