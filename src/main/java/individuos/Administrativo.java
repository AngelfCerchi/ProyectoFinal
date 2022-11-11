package individuos;

import clinica.Clinica;
import clinica.Especialidad;
import clinica.Turno;
import clinica.prestacion.Prestacion;
import clinica.ubicaciones.Ubicacion;
import enums.TipoServicio;

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

    public Prestacion crearPrestacion(String nombre, Boolean esEstudio, Especialidad especialidad, Ubicacion ubicacion, Doctor doctor) {
        Prestacion nuevaPrestacion = new Prestacion(nombre, esEstudio, especialidad, ubicacion);
        nuevaPrestacion.setDoctorAsociado(doctor);
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
                turno.setPrestacionBrindada(prestacion);
                t.setEspecialidadDelTurno(prestacion.getEspecialidad());
                t.setUbicacionTurno(prestacion.getUbicacion());
                t.setDoctor(prestacion.getDoctorAsociado());
                return t;
            }
        }
        return null;
    }

    public Doctor crearDoctor(String nombreDoctor, String apellidoDoctor, String dni) {
        Doctor doctor = new Doctor(nombreDoctor, apellidoDoctor, dni);
        clinica.getDoctores().add(doctor);
        return doctor;
    }

    public void pagarTurno(Turno turnoAtendido, String tipoMedioDePago) {
        turnoAtendido.setTurnoPagado(true);
        turnoAtendido.setTipoDePago(tipoMedioDePago);
    }

    public Paciente crearPaciente(String nombre, String apellido, String dniPaciente, TipoServicio tipoServicio) {
        Paciente paciente = new Paciente(nombre, apellido, dniPaciente, tipoServicio);
        clinica.getPacientes().add(paciente);
        return paciente;
    }
}
