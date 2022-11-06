package clinica;

import clinica.prestacion.Prestacion;
import individuos.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Clinica {
    private static Clinica instance;
    private Director director;
    private ArrayList<Paciente> pacientes;
    private ArrayList<Especialidad> especialidades;
    private HashMap<Prestacion, ArrayList<Turno>> turnos;
    private HashMap<Prestacion, ArrayList<Turno>> sobreTurnos;

    private Clinica() {
    }

    public static Clinica getInstance() {
        if (instance == null) {
            instance = new Clinica();
        }
        return instance;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(ArrayList<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public ArrayList<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(ArrayList<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

    public HashMap<Prestacion, ArrayList<Turno>> getTurnos() {
        return turnos;
    }

    public void setTurnos(HashMap<Prestacion, ArrayList<Turno>> turnos) {
        this.turnos = turnos;
    }

    public HashMap<Prestacion, ArrayList<Turno>> getSobreTurnos() {
        return sobreTurnos;
    }

    public void setSobreTurnos(HashMap<Prestacion, ArrayList<Turno>> sobreTurnos) {
        this.sobreTurnos = sobreTurnos;
    }

    public void agregarPrestacion(Prestacion nuevaPrestacion, Especialidad especialidad) {

    }

//    public String generarReporteMensual(Doctor doctor) {
//        StringBuilder reporte = new StringBuilder();
//        reporte.append(doctor.getNombreCompleto()).append("\n");
//        for (Especialidad especialidad : doctor.getEspecialidades()) {
//            reporte.append(especialidad.getNombre()).append("\n");
//            getTurnosAsistidosPorPrestacion(especialidad).stream().forEach(x -> reporte.append(x.getPrestacionBrindada().toString()).append("\n"));
//        }
//        return reporte.toString();
//    }

    public List<Turno> getTurnosDisponiblesPorPrestacion(Prestacion prestacion) {
        return obtenerTurnosDisponiblesSegunEspeciliadadYMapa(turnos, prestacion);
    }

    public List<Turno> getSobreTurnosDisponiblesPorPrestacion(Prestacion prestacion) {
        return obtenerTurnosDisponiblesSegunEspeciliadadYMapa(sobreTurnos, prestacion);
    }

    public List<Turno> getTurnosAsistidosPorPrestacion(Prestacion prestacion) {
        return obtenerTurnosAsistidosSegunEspeciliadadYMapa(turnos, prestacion);
    }

    public List<Turno> getSobreTurnosAsistidosPorPrestacion(Prestacion prestacion) {
        return obtenerTurnosAsistidosSegunEspeciliadadYMapa(sobreTurnos, prestacion);
    }

    public List<Prestacion> getPrestacionesActivasPorEspecialidad(Especialidad especialidad) {
        return especialidad.getPrestaciones().stream().filter(Prestacion::getActiva).collect(Collectors.toList());
    }

    public Paciente getPacientePorDni(String dni){
        return getPacientes().stream().filter(p -> p.getDni().equals(dni)).findFirst().get();
    }

    private List<Turno> obtenerTurnosAsistidosSegunEspeciliadadYMapa(HashMap<Prestacion, ArrayList<Turno>> turnos, Prestacion especialidad) {
        return turnos.get(especialidad).stream().filter(x -> x.getAusente().equals(false)).collect(Collectors.toList());
    }

    private List<Turno> obtenerTurnosDisponiblesSegunEspeciliadadYMapa(HashMap<Prestacion, ArrayList<Turno>> turnos, Prestacion especialidad) {
        return turnos.get(especialidad).stream().filter(Turno::getDisponible).collect(Collectors.toList());
    }
}
