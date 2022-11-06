package clinica;

import clinica.prestacion.Prestacion;

import java.util.ArrayList;
import java.util.List;

public class Especialidad {
    public static int nroEspecialidad = 0;
    private String nombre;
    private ArrayList<Prestacion> prestaciones = new ArrayList<>();

    public Especialidad(String nombre) {
        this.nombre = nombre;
        incrementarNro();
    }

    public int getNroEspecialidad() {
        return nroEspecialidad;
    }

    private void incrementarNro() {
        nroEspecialidad++;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Especialidad{" +
                "Nro='" + nroEspecialidad + '\'' +
                "nombre='" + nombre + '\'' +
                '}';
    }


}
