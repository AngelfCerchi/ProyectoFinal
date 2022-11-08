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

    public LocalDateTime getFechaYHoraRealizacion() {
        return fechaYHoraRealizacion;
    }

    public void setFechaYHoraRealizacion(LocalDateTime fechaYHoraRealizacion) {
        this.fechaYHoraRealizacion = fechaYHoraRealizacion;
    }

    public boolean isAsistio() {
        return asistio;
    }

    public void setAsistio(boolean asistio) {
        this.asistio = asistio;
    }

    @Override
    public String toString() {
        return "Estudio{" +
                "fechaYHoraRealizacion=" + fechaYHoraRealizacion +
                ", asistio=" + asistio +
                '}';
    }
}
