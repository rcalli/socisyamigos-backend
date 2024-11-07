package pe.com.edu.socisyamigos.serviceImpl;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.com.edu.socisyamigos.entity.Estudiante;
import pe.com.edu.socisyamigos.entity.Persona;
import pe.com.edu.socisyamigos.repository.EstudianteRepository;
import pe.com.edu.socisyamigos.repository.PersonaRepository;
import pe.com.edu.socisyamigos.service.EstudianteService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository categoriaRepository;
    @Override
    public Estudiante create(Estudiante cat) {

        return categoriaRepository.save(cat);
    }

    @Override
    public Estudiante update(Estudiante cat) {

        return categoriaRepository.save(cat);
    }

    @Override
    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public Optional<Estudiante> read(Long id) {

        return categoriaRepository.findById(id);
    }

    @Override
    public List<Estudiante> readAll() {

        return categoriaRepository.findAll();
    }

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    public void cargarDatosDesdeExcel(MultipartFile file) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Saltar encabezado

            // Leer datos de la fila y asignarlos a los atributos de Persona y Estudiante
            String nombre = row.getCell(0).getStringCellValue();
            String apepat = row.getCell(1).getStringCellValue();
            String apemat = row.getCell(2).getStringCellValue();
            String direccion = row.getCell(3).getStringCellValue();
            String dni = row.getCell(4).getStringCellValue();
            String correo = row.getCell(5).getStringCellValue();
            String telefono = row.getCell(6).getStringCellValue();
            int estadoPersona = (int) row.getCell(7).getNumericCellValue();

            // Crear entidad Persona y guardar
            Persona persona = new Persona();
            persona.setNombre(nombre);
            persona.setApepat(apepat);
            persona.setApemat(apemat);
            persona.setDireccion(direccion);
            persona.setDni(dni);
            persona.setCorreo(correo);
            persona.setTelefono(telefono);
            persona.setEstado(estadoPersona);
            personaRepository.save(persona); // Guardamos la persona y obtenemos el ID generado

            // Asumimos que las siguientes columnas corresponden a Estudiante
            long idEstudiante = (int) row.getCell(8).getNumericCellValue();
            String codigo = row.getCell(9).getStringCellValue();
            int estadoEstudiante = (int) row.getCell(10).getNumericCellValue();

            // Crear entidad Estudiante y guardar
            Estudiante estudiante = new Estudiante();
            estudiante.setId(idEstudiante);
            estudiante.setPersona( persona); // RelaciÃ³n con Persona
            estudiante.setCodigo(codigo);
            estudiante.setEstado(estadoEstudiante);
            estudianteRepository.save(estudiante);
        }
        workbook.close();
    }

}