package clinica;

import clinica.prestacion.Prestacion;
import clinica.ubicaciones.Ubicacion;
import individuos.Doctor;
import individuos.Persona;

import java.time.LocalDateTime;

public class Turno {
    private static int nroTurno = 0;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private Boolean asistio;
    private Boolean disponible;
    private Persona pacienteAsociado;
    private Doctor doctor;
    private Prestacion prestacionBrindada;
    private Especialidad especialidadDelTurno;
    private Ubicacion ubicacionTurno;

    public Turno(LocalDateTime inicio, LocalDateTime fin) {
        this.inicio = inicio;
        this.fin = fin;
        this.asistio = false;
        this.disponible = true;
        nroTurno++;
    }

    public static int getNroTurno() {
        return nroTurno;
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

    public Boolean getAsistio() {
        return asistio;
    }

    public void setAsistio(Boolean asistio) {
        this.asistio = asistio;
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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Especialidad getEspecialidadDelTurno() {
        return especialidadDelTurno;
    }

    public void setEspecialidadDelTurno(Especialidad especialidadDelTurno) {
        this.especialidadDelTurno = especialidadDelTurno;
    }

    public Ubicacion getUbicacionTurno() {
        return ubicacionTurno;
    }

    public void setUbicacionTurno(Ubicacion ubicacionTurno) {
        this.ubicacionTurno = ubicacionTurno;
    }

    public void registrarAsistenciaPaciente() {
        setAsistio(false);
    }

    public void asociarPaciente(Persona paciente) {
        setPacienteAsociado(paciente);
    }

    public String getHorario() {
        return getInicio() + " - " + getFin();
    }

    @Override
    public String toString() {
        return "Turno{" +
                "inicio=" + inicio +
                ", fin=" + fin +
                ", doctor=" + doctor +
                ", prestacionBrindada=" + prestacionBrindada +
                ", especialidadDelTurno=" + especialidadDelTurno +
                ", asistio=" + asistio +
                ", ubicacionTurno=" + ubicacionTurno +
                '}';
    }
}
