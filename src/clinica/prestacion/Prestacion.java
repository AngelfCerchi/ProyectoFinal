package clinica.prestacion;

import clinica.Especialidad;
import clinica.ubicaciones.Ubicacion;
import individuos.Doctor;

public class Prestacion {
    public static int nroPrestacion;
    private String nombre;
    private Doctor doctorAsociado;
    private Boolean activa;
    private Boolean esEstudio;
    private Especialidad especialidad;
    private Ubicacion ubicacion;

    public Prestacion(String nombre) {
        this.nombre = nombre;
        this.activa = true;
        nroPrestacion++;
    }

    public Prestacion(String nombre, Boolean esEstudio, Especialidad especialidad, Ubicacion ubicacion) {
        this.nombre = nombre;
        this.activa = true;
        this.esEstudio = esEstudio;
        this.especialidad = especialidad;
        this.ubicacion = ubicacion;
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

    public static int getNroPrestacion() {
        return nroPrestacion;
    }

    public Boolean getEsEstudio() {
        return esEstudio;
    }

    public void setEsEstudio(Boolean esEstudio) {
        this.esEstudio = esEstudio;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    private String activaFormat() {
        return (activa) ? "Si" : "No;";
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + " Doctor: " + doctorAsociado + " Activa: " + activaFormat();
    }
}
