package pe.com.edu.socisyamigos.serviceImpl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.com.edu.socisyamigos.entity.*;
import pe.com.edu.socisyamigos.repository.*;
import pe.com.edu.socisyamigos.service.EstudianteService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository categoriaRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private Plan_CarreraRepository plan_carreraRepository;

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

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
    private int[] extractAnioYEtapaFromPlan(String nombrePlan) {
        try {
            // Suponiendo que el formato es "YYYY-X" (ej. "2024-2")
            String[] partes = nombrePlan.split("-");
            int anio = Integer.parseInt(partes[0]); // Primera parte es el año
            int etapa = Integer.parseInt(partes[1]); // Segunda parte es la etapa
            return new int[]{anio, etapa};
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            // Manejo de errores en caso de formato inesperado
            System.err.println("Error al extraer año y etapa del nombre del plan: " + e.getMessage());
            return new int[]{0, 0}; // Valores por defecto en caso de error
        }
    }


    public void cargarDatosDesdeExcel(MultipartFile file) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Saltar encabezado

                try {
                    // Leer y validar datos de Persona
                    String nombre = getCellValueAsString(row.getCell(0));
                    String apepat = getCellValueAsString(row.getCell(1));
                    String apemat = getCellValueAsString(row.getCell(2));
                    String direccion = getCellValueAsString(row.getCell(3));
                    String dni = getCellValueAsString(row.getCell(4));
                    String correo = getCellValueAsString(row.getCell(5));
                    String telefono = getCellValueAsString(row.getCell(6));
                    int estadoPersona = 1;

                    if (nombre.isEmpty() || apepat.isEmpty() || dni.isEmpty() || correo.isEmpty() || estadoPersona == 0) {
                        System.err.println("Datos incompletos para Persona en la fila " + row.getRowNum());
                        continue;
                    }

                    // Crear o recuperar entidad Persona
                    Persona persona = personaRepository.findByDni(dni)
                            .orElseGet(() -> {
                                Persona newPersona = new Persona();
                                newPersona.setNombre(nombre);
                                newPersona.setApepat(apepat);
                                newPersona.setApemat(apemat);
                                newPersona.setDireccion(direccion);
                                newPersona.setDni(dni);
                                newPersona.setCorreo(correo);
                                newPersona.setTelefono(telefono);
                                newPersona.setEstado(estadoPersona);
                                return personaRepository.save(newPersona);
                            });

                    // Crear o recuperar Usuario para la Persona
                    usuarioRepository.findByPersona(persona).orElseGet(() -> {
                        Usuario usuario = new Usuario();
                        usuario.setPersona(persona);
                        usuario.setUsername(generateUsername(nombre, apepat)); // Generar username
                        usuario.setPassword(dni); // La contraseña es el DNI
                        usuario.setEstado(1); // Estado inicial 1
                        return usuarioRepository.save(usuario);
                    });

                    // Leer datos de Estudiante
                    String codigo = getCellValueAsString(row.getCell(7));
                    if (codigo != null && !codigo.isEmpty()) {
                        int estadoEstudiante = 1;
                        // Crear o recuperar Estudiante
                        Estudiante estudiante = estudianteRepository.findByCodigo(codigo)
                                .orElseGet(() -> {
                                    Estudiante newEstudiante = new Estudiante();
                                    newEstudiante.setPersona(persona);
                                    newEstudiante.setCodigo(codigo);
                                    newEstudiante.setEstado(estadoEstudiante);
                                    return estudianteRepository.save(newEstudiante);
                                });

                        // Leer datos para Plan, Carrera y Matricula
                        String nombrePlan = getCellValueAsString(row.getCell(8));
                        long idCarrera = (long) getCellValueAsNumeric(row.getCell(9));
                        if (nombrePlan == null || nombrePlan.isEmpty() || idCarrera == 0) {
                            System.err.println("Datos incompletos para Plan o Carrera en la fila " + row.getRowNum());
                            continue;
                        }

                        int horasTotales = 0;
                        int estadoMatricula = 1;
                        int[] anioEtapa = extractAnioYEtapaFromPlan(nombrePlan); // Extraer año y etapa del nombre del plan
                        int anioPlan = anioEtapa[0];
                        int etapa = anioEtapa[1];
                        int estadoPlan = 1;

                        // Buscar o crear Plan en base a los campos únicos
                        Plan plan = planRepository.findByNombreAndAnioAndEtapaAndEstado(nombrePlan, anioPlan, etapa, estadoPlan)
                                .orElseGet(() -> {
                                    Plan newPlan = new Plan();
                                    newPlan.setNombre(nombrePlan);
                                    newPlan.setAnio(anioPlan);
                                    newPlan.setEtapa(etapa);
                                    newPlan.setEstado(estadoPlan);
                                    return planRepository.save(newPlan);
                                });

                        Carrera carrera = carreraRepository.findById(idCarrera)
                                .orElseThrow(() -> new RuntimeException("Carrera no encontrada con ID: " + idCarrera));

                        // Verificar duplicados en Plan_Carrera usando Plan y Carrera
                        Optional<Plan_Carrera> existingPlanCarrera = plan_carreraRepository.findByPlanAndCarrera(plan, carrera);
                        Plan_Carrera planCarrera;
                        if (existingPlanCarrera.isPresent()) {
                            planCarrera = existingPlanCarrera.get();
                        } else {
                            // Crear nuevo Plan_Carrera si no existe
                            planCarrera = new Plan_Carrera();
                            planCarrera.setPlan(plan);
                            planCarrera.setCarrera(carrera);
                            planCarrera.setEstado(1); // Estado predeterminado
                            planCarrera = plan_carreraRepository.save(planCarrera);
                        }

                        // Evitar duplicados en Matricula
                        Optional<Matricula> existingMatricula = matriculaRepository.findByEstudianteAndPlanCarreraAndAnio(estudiante, planCarrera, anioPlan);
                        if (existingMatricula.isPresent()) {
                            System.err.println("Matrícula duplicada en la fila " + row.getRowNum());
                            continue;
                        }

                        // Crear y guardar Matricula
                        Matricula matricula = new Matricula();
                        matricula.setEstudiante(estudiante);
                        matricula.setPlan_carrera(planCarrera);
                        matricula.setHoras_totales(horasTotales);
                        matricula.setAnio(anioPlan);
                        matricula.setEstado(estadoMatricula);
                        matriculaRepository.save(matricula);
                    }

                } catch (Exception e) {
                    System.err.println("Error en la fila " + row.getRowNum() + ": " + e.getMessage());
                }
            }
        }
    }

    private String generateUsername(String nombre, String apepat) {
        return (nombre.toLowerCase() + "." + apepat.toLowerCase()).replaceAll("\\s+", "");
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
