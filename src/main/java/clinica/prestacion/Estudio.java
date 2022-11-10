package clinica.prestacion;

import java.time.LocalDateTime;

public class Estudio extends Prestacion {

    private LocalDateTime fechaYHoraRealizacion;
    private boolean asistio;

    public Estudio(String nombre) {
        super(nombre);
        this.asistio = false;
        this.setEsEstudio(true);
    }

    public void setFechaYHoraRealizacion(LocalDateTime fechaYHoraRealizacion) {
        this.fechaYHoraRealizacion = fechaYHoraRealizacion;
    }

    public String asistioToString() {
        return asistio ? "Si" : "No";
    }

    public void setAsistio(boolean asistio) {
        this.asistio = asistio;
    }

    public boolean isAsistio() {
        return asistio;
    }

    public String getFechaToString() {
        return this.fechaYHoraRealizacion == null ? "No hay fecha" : fechaYHoraRealizacion.toString();
    }

    public LocalDateTime getFechaYHoraRealizacion() {
        return fechaYHoraRealizacion;
    }

    @Override
    public String toString() {
        return "Estudio{" +
                " nombre: " + getNombre() +
                " - fechaYHoraRealizacion: " + getFechaToString() +
                " - asistio: " + asistioToString() +
                '}';
    }
}
