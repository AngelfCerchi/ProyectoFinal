package clinica.prestacion;

import individuos.Doctor;

public class Prestacion {
    public static int nroPrestacion;
    private String nombre;
    private Doctor doctorAsociado;
    private Boolean activa;

    public Prestacion(String nombre, Doctor doctorAsociado) {
        this.nombre = nombre;
        this.doctorAsociado = doctorAsociado;
        this.activa = true;
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
