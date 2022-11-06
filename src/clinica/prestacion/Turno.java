package clinica.prestacion;

import clinica.Especialidad;
import individuos.Persona;

import java.time.LocalDateTime;

public class Turno {
    private static int nroTurno = 0;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private Boolean ausente;
    private Boolean disponible;
    private Persona pacienteAsociado;
    private Prestacion prestacionBrindada;
    private Especialidad especialidadDelTurno;

    public Turno(LocalDateTime inicio, LocalDateTime fin) {
        this.inicio = inicio;
        this.fin = fin;
        this.ausente = false;
        this.disponible = true;
        incrementarN();
    }

    private static void incrementarN() {
        nroTurno++;
    }

    public static int getNroTurno() {
        return nroTurno;
    }

    public static void setNroTurno(int nroTurno) {
        Turno.nroTurno = nroTurno;
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

    public Prestacion getPrestacionBrindada() {
        return prestacionBrindada;
    }

    public void setPrestacionBrindada(Prestacion prestacionBrindada) {
        this.prestacionBrindada = prestacionBrindada;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Persona getPacienteAsociado() {
        return pacienteAsociado;
    }

    public void setPacienteAsociado(Persona pacienteAsociado) {
        this.pacienteAsociado = pacienteAsociado;
    }

    public void registrarAsistenciaPaciente() {
        setAusente(false);
    }

    public void asociarPaciente(Persona paciente) {
        setPacienteAsociado(paciente);
    }

    @Override
    public String toString() {
        return "Turno{" +
                "inicio=" + inicio +
                ", fin=" + fin +
                ", ausente=" + ausente +
                '}';
    }
}
