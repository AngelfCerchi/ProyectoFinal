package individuos;

import clinica.Clinica;
import clinica.Especialidad;
import clinica.Turno;
import clinica.prestacion.Prestacion;
import clinica.ubicaciones.Ubicacion;

import java.util.*;

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

    public Prestacion crearPrestacion(String nombre, Boolean esEstudio, Especialidad especialidad, Ubicacion ubicacion) {
        Prestacion nuevaPrestacion = new Prestacion(nombre, esEstudio, especialidad, ubicacion);
        clinica.agregarPrestacion(nuevaPrestacion);
        return nuevaPrestacion;
    }

    public Turno darTurno(Paciente paciente, Turno turno, Prestacion prestacion, boolean esSobreTurno) {
        List<Turno> listaTurnos;
        if (esSobreTurno) {
            listaTurnos = clinica.getSobreTurnosDisponiblesPorPrestacion(prestacion);
        } else {
            listaTurnos = clinica.getTurnosDisponiblesPorPrestacion(prestacion);
        }
        for (Turno t : listaTurnos) {
            if (t.getHorario().equals(turno.getHorario())) {
                t.asociarPaciente(paciente);
                t.setDisponible(false);
                t.setEspecialidadDelTurno(prestacion.getEspecialidad());
                t.setUbicacionTurno(prestacion.getUbicacion());
                t.setDoctor(prestacion.getDoctorAsociado());
                return t;
            }
        }
        return null;
    }
}
