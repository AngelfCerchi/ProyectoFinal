package individuos;

import clinica.Clinica;
import clinica.Especialidad;
import clinica.prestacion.Prestacion;
import clinica.Turno;
import clinica.ubicaciones.Ubicacion;

import java.util.List;

public class Administrativo extends Persona {

    private final Clinica clinica = Clinica.getInstance();

    /**
     * CONSTRUCTOR
     *
     * @param nombre
     * @param apellido
     * @param dni
     **/
    public Administrativo(String nombre, String apellido, String dni) {
        super(nombre, apellido, dni);
    }

    public void crearPrestacion(String nombre, Especialidad especialidad) {
        Prestacion nuevaPrestacion = new Prestacion(nombre);
        clinica.agregarPrestacion(nuevaPrestacion, especialidad);
    }

    public Turno darTurno(Paciente paciente, Turno turno, Ubicacion ubicacion, Doctor doctor) {
        turno.setUbicacionTurno(ubicacion);
        turno.setDoctor(doctor);
        turno.asociarPaciente(paciente);
        paciente.agregarTurno(turno);
        return turno;
    }

    public List<Prestacion> prestacionesActivasPorEspecialidad(Especialidad especialidad) {
        return clinica.getPrestacionesActivasPorEspecialidad(especialidad);
    }

    public List<Turno> turnosDisponiblesPorPrestacion(Prestacion prestacion) {
        return clinica.getTurnosDisponiblesPorPrestacion(prestacion);
    }

    public List<Turno> turnosTomados(Paciente paciente) {
        return paciente.getTurnosAsignados();
    }
}
