package clinica;

import clinica.prestacion.Prestacion;

import java.util.ArrayList;

public class Especialidad {
    private String nombre;
    private Boolean activa;
    private ArrayList<Prestacion> prestaciones;

    public Especialidad(String nombre) {
        this.nombre = nombre;
        this.activa = true;
        this.prestaciones = new ArrayList<>();

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActiva() {
        return activa;
    }

    public ArrayList<Prestacion> getPrestaciones() {
        return prestaciones;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
