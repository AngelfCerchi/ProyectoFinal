package clinica.ubicaciones;

import clinica.prestacion.Estudio;

import java.util.ArrayList;

public abstract class Ubicacion {

    private String nombre;
    private static int nroUbicacion = 0;

    public Ubicacion(String nombre) {
        this.nombre = nombre;
        nroUbicacion++;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static int getNroUbicacion() {
        return nroUbicacion;
    }

    public static void setNroUbicacion(int nroUbicacion) {
        Ubicacion.nroUbicacion = nroUbicacion;
    }

    public ArrayList<Estudio> estudiosDisponibles() {
        return null;
    }
}
