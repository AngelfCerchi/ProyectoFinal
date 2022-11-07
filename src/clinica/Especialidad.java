package clinica;

import clinica.prestacion.Prestacion;

import java.util.ArrayList;

public class Especialidad {
    public static int nroEspecialidad = 0;
    private String nombre;
    private Boolean activa;
    private ArrayList<Prestacion> prestaciones;

    public Especialidad(String nombre) {
        this.nombre = nombre;
        this.activa = true;
        this.prestaciones = new ArrayList<>();
        nroEspecialidad++;
    }

    public int getNroEspecialidad() {
        return nroEspecialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Prestacion> getPrestaciones() {
        return prestaciones;
    }

    public void setPrestaciones(ArrayList<Prestacion> prestaciones) {
        this.prestaciones = prestaciones;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    @Override
    public String toString() {
        return "Especialidad{" +
                "Nro='" + nroEspecialidad + '\'' +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
