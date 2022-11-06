package clinica.prestacion;

import individuos.Doctor;

import java.util.ArrayList;

public class Prestacion {
    public static int nroPrestacion;
    private String nombre;
    private Doctor doctorAsociado;
    private Boolean activa;
    private ArrayList<CompositeSalud> prescripciones = new ArrayList<CompositeSalud>();

    public Prestacion(String nombre) {
        this.nombre = nombre;
        this.activa = true;
        nroPrestacion++;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Doctor getDoctorAsociado() {
        return doctorAsociado;
    }

    public void setDoctorAsociado(Doctor doctorAsociado) {
        this.doctorAsociado = doctorAsociado;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }
    @Override
    public String toString() {
        return "Prestacion{" +
                "nroPrestacion'" + this.nroPrestacion+ '\'' +
                "nombre='" + this.nombre + '\'' +
                ", doctor=" + this.doctorAsociado +
                ", activa=" + this.activa +
                '}';
    }
}
