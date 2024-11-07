package pe.com.edu.socisyamigos.service;

import pe.com.edu.socisyamigos.entity.Estudiante;

import java.util.List;
import java.util.Optional;

public interface EstudianteService {
    public Estudiante create(Estudiante cat);
    public Estudiante update(Estudiante cat);
    public void delete(Long id);
    public Optional<Estudiante> read(Long id);
    public List<Estudiante> readAll();
}
