package clinica.prestacion;

import individuos.Doctor;
import clinica.Especialidad;

public class Prestacion {
    public static int nroPrestacion;
    private String nombre;
    private Doctor doctor;
    private Boolean activa = false;

    public Prestacion(String nombre, Boolean activa) {
        this.nombre = nombre;
        this.activa = activa;
        incrementarNro();
    }

    public Prestacion(String nombre, Doctor doctor, Boolean activa) {
        this.nombre = nombre;
        this.doctor = doctor;
        this.activa = activa;
        incrementarNro();
    }

    private static void incrementarNro(){
        nroPrestacion += 1;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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
                ", doctor=" + this.doctor +
                ", activa=" + this.activa +
                '}';
    }
}
