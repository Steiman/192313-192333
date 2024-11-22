import java.util.*;

class Materia {
    private String nombre;
    private String codigo;
    private List<String> horariosDisponibles;

    public Materia(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.horariosDisponibles = new ArrayList<>();
    }

    public void agregarHorario(String horario) {
        horariosDisponibles.add(horario);
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public List<String> getHorariosDisponibles() {
        return horariosDisponibles;
    }

    @Override
    public String toString() {
        return nombre + " (" + codigo + ")";
    }
}

class Inscripcion {
    private Materia materia;
    private String horario;

    public Inscripcion(Materia materia, String horario) {
        this.materia = materia;
        this.horario = horario;
    }

    public Materia getMateria() {
        return materia;
    }

    public String getHorario() {
        return horario;
    }
}

class Estudiante {
    private String nombre;
    private String id;
    private List<Inscripcion> inscripciones;

    public Estudiante(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        this.inscripciones = new ArrayList<>();
    }

    public boolean inscribirMateria(Materia materia, String horario) {
        
        if (!materia.getHorariosDisponibles().contains(horario)) {
            System.out.println("Error: Horario no disponible para " + materia.getNombre());
            return false;
        }

        
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getMateria().getCodigo().equals(materia.getCodigo())) {
                System.out.println("Error: El estudiante ya está inscrito en " + materia.getNombre());
                return false;
            }
        }

        
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getHorario().equals(horario)) {
                System.out.println("Error: Conflicto de horario con " + inscripcion.getMateria().getNombre());
                return false;
            }
        }

        
        inscripciones.add(new Inscripcion(materia, horario));
        System.out.println("Inscripción exitosa: " + nombre + " en " + materia.getNombre() + " - Horario: " + horario);
        return true;
    }

    public void mostrarHorario() {
        System.out.println("\nHorario de " + nombre + " (ID: " + id + "):");
        for (Inscripcion inscripcion : inscripciones) {
            System.out.println(inscripcion.getMateria() + " - Horario: " + inscripcion.getHorario());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Iniciando Sistema Académico ===\n");
        
        
        System.out.println("1. Registro de Materias:");
        List<Materia> materias = new ArrayList<>();
        
        Materia algebra = new Materia("Álgebra", "MAT101");
        algebra.agregarHorario("Lunes 7:00-9:00");
        algebra.agregarHorario("Martes 7:00-9:00");
        materias.add(algebra);
        System.out.println("Registrada: " + algebra);

        Materia calculo = new Materia("Cálculo", "MAT102");
        calculo.agregarHorario("Lunes 7:00-9:00");
        calculo.agregarHorario("Martes 9:00-11:00");
        materias.add(calculo);
        System.out.println("Registrada: " + calculo);

        Materia fisica = new Materia("Física", "FIS101");
        fisica.agregarHorario("Martes 7:00-9:00");
        fisica.agregarHorario("Miércoles 7:00-9:00");
        materias.add(fisica);
        System.out.println("Registrada: " + fisica);

        
        System.out.println("\n2. Inscripción de Estudiantes:");
        List<Estudiante> estudiantes = new ArrayList<>();
        
        Estudiante estudiante1 = new Estudiante("Yanderson Ortiz", "192333");
        Estudiante estudiante2 = new Estudiante("Steiman Sanchez", "192313");
        estudiantes.add(estudiante1);
        estudiantes.add(estudiante2);

        
        System.out.println("\n3. Verificación de Horarios:");
        
        
        estudiante1.inscribirMateria(algebra, "Lunes 7:00-9:00");
        estudiante1.inscribirMateria(fisica, "Miércoles 7:00-9:00");

        
        estudiante1.inscribirMateria(calculo, "Lunes 7:00-9:00");

        
        estudiante2.inscribirMateria(calculo, "Martes 9:00-11:00");
        estudiante2.inscribirMateria(fisica, "Martes 7:00-9:00");

        
        System.out.println("\nHorarios Finales:");
        estudiante1.mostrarHorario();
        estudiante2.mostrarHorario();
    }
}
