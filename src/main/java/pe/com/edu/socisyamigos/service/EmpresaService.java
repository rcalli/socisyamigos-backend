
package pe.com.edu.socisyamigos.service;

import pe.com.edu.socisyamigos.entity.Empresa;

import java.util.List;
import java.util.Optional;

public interface EmpresaService {
    public Empresa create(Empresa cat);
    public Empresa update(Empresa cat);
    public void delete(Long id);
    public Optional<Empresa> read(Long id);
    public List<Empresa> readAll();
}
