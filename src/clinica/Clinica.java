package clinica;

import clinica.prestacion.Prestacion;
import individuos.Director;
import individuos.Doctor;
import individuos.Paciente;

import java.util.*;
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
        for (Especialidad  e: getEspecialidades()) {
            if(e.getNombre().equals(especialidad.getNombre())){
                System.out.println("Se agregara la prestacion nueva para la especialidad "+ especialidad.getNombre());
                e.getPrestaciones().add(nuevaPrestacion);
            }
            System.out.println("No existe la especialidad para la que se desea agregar la especliadad. PELOTUDO!");
        }
    }

    public String reportePrestacionesPorDoctor(Doctor doctor) {
        //TODO probar esto
        int estudios = cantidadPrestaciones(doctor, true);
        int prestaciones = cantidadPrestaciones(doctor, false);
        return "El doctor " + doctor.getNombreCompleto() + " realizo " + estudios + " estudios y " + prestaciones + " prestaciones";
    }



    private int cantidadPrestaciones(Doctor doctor, boolean esEstudio){
        int cantidad = 0;

        for (Prestacion p: this.turnos.keySet()) {
            for (Turno t : this.turnos.get(p)){
                if(t.getDoctor().getDni().equals(doctor.getDni()) && !t.getAusente() && t.getPrestacionBrindada().getEsEstudio().equals(esEstudio)){
                    cantidad++;
                }
            }
        }
        return cantidad;
    }



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

    public List<Prestacion> getPrestacionesActivas() {
        List<Prestacion> prestaciones = new ArrayList<>();
        getEspecialidades().stream().forEach(x -> prestaciones.addAll(getPrestacionesActivasPorEspecialidad(x)));
        return prestaciones;
    }

    public Paciente getPacientePorDni(String dni) {
        return getPacientes().stream().filter(p -> p.getDni().equals(dni)).findFirst().get();
    }

    private List<Turno> obtenerTurnosAsistidosSegunEspeciliadadYMapa(HashMap<Prestacion, ArrayList<Turno>> turnos, Prestacion especialidad) {
        return turnos.get(especialidad).stream().filter(x -> x.getAusente().equals(false)).collect(Collectors.toList());
    }

    private List<Turno> obtenerTurnosDisponiblesSegunEspeciliadadYMapa(HashMap<Prestacion, ArrayList<Turno>> turnos, Prestacion especialidad) {
        return turnos.get(especialidad).stream().filter(Turno::getDisponible).collect(Collectors.toList());
    }


}
