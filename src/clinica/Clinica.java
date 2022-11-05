package clinica;

import clinica.prestacion.Prestacion;
import individuos.*;

public class Clinica {
    private static Clinica instance;
    private Prestacion[] prestaciones; //Puede ser areas medicas //TODO por?
    private Persona[] pacientes;
    private Director director;
    private Administrativo[] administrativos;
    private Medico[] medicos;


    private Clinica() {}
    public static Clinica getInstance() {
        if (instance == null) {
            instance = new Clinica();
        }
        return instance;
    }
    public Prestacion[] getPrestaciones() {
        return prestaciones;
    }

    public void setPrestaciones(Prestacion[] prestaciones) {
        this.prestaciones = prestaciones;
    }

    public Persona[] getPacientes() {
        return pacientes;
    }

    public void setPacientes(Persona[] pacientes) {
        this.pacientes = pacientes;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Administrativo[] getAdministrativos() {
        return administrativos;
    }

    public void setAdministrativos(Administrativo[] administrativos) {
        this.administrativos = administrativos;
    }

    public Medico[] getDoctores() {
        return medicos;
    }

    public void setMedicos(Medico[] medicos) {
        this.medicos = medicos;
    }
}
