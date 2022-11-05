package clinica;

import clinica.prestacion.Prestacion;
import clinica.prestacion.Turno;
import individuos.*;

import java.util.ArrayList;
import java.util.List;

public class Clinica {
    private static Clinica instance;
    private Director director;
    private ArrayList<Prestacion> prestaciones = new ArrayList<Prestacion>();
    private ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
    private ArrayList<Turno> turnosDisponibles = new ArrayList<Turno>();
    private ArrayList<Administrativo> administrativos = new ArrayList<Administrativo>();
    private ArrayList<Doctor> doctores = new ArrayList<Doctor>();


    private Clinica() {}
    public static Clinica getInstance() {
        if (instance == null) {
            instance = new Clinica();
        }
        return instance;
    }

    public static void setInstance(Clinica instance) {
        Clinica.instance = instance;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Prestacion> getPrestaciones() {
        return prestaciones;
    }

    public void setPrestaciones(ArrayList prestaciones) {
        this.prestaciones = prestaciones;
    }

    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(ArrayList<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public ArrayList<Turno> getTurnosDisponibles() {
        return turnosDisponibles;
    }

    public void setTurnosDisponibles(ArrayList<Turno> turnosDisponibles) {
        this.turnosDisponibles = turnosDisponibles;
    }

    public ArrayList<Administrativo> getAdministrativos() {
        return administrativos;
    }

    public void setAdministrativos(ArrayList<Administrativo> administrativos) {
        this.administrativos = administrativos;
    }

    public ArrayList<Doctor> getDoctores() {
        return doctores;
    }

    public void setDoctores(ArrayList<Doctor> doctores) {
        this.doctores = doctores;
    }

    public void agregarPrestacion(Prestacion nuevaPrestacion){
        this.prestaciones.add(nuevaPrestacion);
    }
    public void elimnarPrestacio(Integer nroPrestacion){
        this.prestaciones.remove(nroPrestacion);
    }

}
