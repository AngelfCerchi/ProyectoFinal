package clinica.prestacion;

import clinica.Especialidad;
import individuos.Persona;

import java.time.LocalDateTime;

public class Turno {
    public static int nTurno = 0;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private Boolean ausente = true;
    private Especialidad especialidad;


    public Turno(LocalDateTime inicio, LocalDateTime fin, Boolean ausente) {
        this.inicio = inicio;
        this.fin = fin;
        this.ausente = ausente;
        incrementarN();
    }

    private static void incrementarN(){
        nTurno++;
    }

    public static int getnTurno() {
        return nTurno;
    }

    public static void setnTurno(int nTurno) {
        Turno.nTurno = nTurno;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public void setFin(LocalDateTime fin) {
        this.fin = fin;
    }

    public Boolean getAusente() {
        return ausente;
    }

    public void setAusente(Boolean ausente) {
        this.ausente = ausente;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "inicio=" + inicio +
                ", fin=" + fin +
                ", ausente=" + ausente +
                ", especialidad=" + especialidad +
                '}';
    }
}
