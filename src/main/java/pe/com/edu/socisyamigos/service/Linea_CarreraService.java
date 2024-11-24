
package pe.com.edu.socisyamigos.service;

import pe.com.edu.socisyamigos.entity.Linea_Carrera;

import java.util.List;
import java.util.Optional;

public interface Linea_CarreraService {
    public Linea_Carrera create(Linea_Carrera cat);
    public Linea_Carrera update(Linea_Carrera cat);
    public void delete(Long id);
    public Optional<Linea_Carrera> read(Long id);
    public List<Linea_Carrera> readAll();
    public List<Linea_Carrera> getLineasCarreraByIdMatricula(Long idMatricula);
}
