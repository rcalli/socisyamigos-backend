package pe.com.edu.socisyamigos.service;

import pe.com.edu.socisyamigos.entity.Persona;


import java.util.List;
import java.util.Optional;

public interface PersonaService {
    public Persona create(Persona cat);
    public Persona update(Persona cat);
    public void delete(Long id);
    public Optional<Persona> read(Long id);
    public List<Persona> readAll();
}
