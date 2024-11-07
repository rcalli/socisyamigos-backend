package pe.com.edu.socisyamigos.serviceImpl;

import org.apache.poi.ss.usermodel.Cell;
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
        try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Saltar encabezado

                try {
                    // Validar y leer datos de Persona
                    String nombre = getCellValueAsString(row.getCell(0));
                    String apepat = getCellValueAsString(row.getCell(1));
                    String apemat = getCellValueAsString(row.getCell(2));
                    String direccion = getCellValueAsString(row.getCell(3));
                    String dni = getCellValueAsString(row.getCell(4));
                    String correo = getCellValueAsString(row.getCell(5));
                    String telefono = getCellValueAsString(row.getCell(6));
                    Integer estadoPersona = (int) getCellValueAsNumeric(row.getCell(7));

                    // Verificar que los campos obligatorios de Persona no estén vacíos o nulos
                    if (nombre.isEmpty() || apepat.isEmpty() || dni.isEmpty() || correo.isEmpty() || estadoPersona == null) {
                        System.err.println("Datos incompletos para Persona en la fila " + row.getRowNum());
                        continue; // Saltar a la siguiente fila si falta algún dato de Persona
                    }

                    // Crear y guardar entidad Persona
                    Persona persona = new Persona();
                    persona.setNombre(nombre);
                    persona.setApepat(apepat);
                    persona.setApemat(apemat);
                    persona.setDireccion(direccion);
                    persona.setDni(dni);
                    persona.setCorreo(correo);
                    persona.setTelefono(telefono);
                    persona.setEstado(estadoPersona);

                    Optional<Persona> existingPersona = personaRepository.findByDni(dni);
                    if (existingPersona.isPresent()) {
                        persona = existingPersona.get();
                    } else {
                        personaRepository.save(persona);
                    }

                    // Leer datos de Estudiante si el campo 'codigo' tiene datos válidos
                    String codigo = getCellValueAsString(row.getCell(9));
                    if (codigo != null && !codigo.isEmpty()) {
                        Long idEstudiante = (long) getCellValueAsNumeric(row.getCell(8));
                        Integer estadoEstudiante = (int) getCellValueAsNumeric(row.getCell(10));

                        // Verificar que los campos obligatorios de Estudiante no estén vacíos o nulos
                        if (estadoEstudiante == null) {
                            System.err.println("Datos incompletos para Estudiante en la fila " + row.getRowNum());
                            continue; // Saltar si faltan datos obligatorios para Estudiante
                        }

                        // Crear y guardar entidad Estudiante
                        Estudiante estudiante = new Estudiante();
                        estudiante.setIdestudiante(idEstudiante);
                        estudiante.setPersona(persona);
                        estudiante.setCodigo(codigo);
                        estudiante.setEstado(estadoEstudiante);

                        Optional<Estudiante> existingEstudiante = estudianteRepository.findByCodigo(codigo);
                        if (existingEstudiante.isPresent()) {
                            estudiante = existingEstudiante.get();
                        } else {
                            estudianteRepository.save(estudiante);
                        }
                    }

                } catch (Exception e) {
                    System.err.println("Error en la fila " + row.getRowNum() + ": " + e.getMessage());
                }
            }
        }
    }

    // Métodos auxiliares para obtener el valor de las celdas
    private String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
    }

    private double getCellValueAsNumeric(Cell cell) {
        if (cell == null) return 0;
        return switch (cell.getCellType()) {
            case NUMERIC -> cell.getNumericCellValue();
            case STRING -> Double.parseDouble(cell.getStringCellValue());
            default -> 0;
        };
    }
}
