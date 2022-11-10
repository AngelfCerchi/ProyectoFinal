package individuos;

import clinica.Clinica;
import clinica.Turno;
import clinica.prestacion.Estudio;
import clinica.prestacion.Prestacion;
import enums.TipoServicio;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class DoctorTest {
    Clinica clinica = Clinica.getInstance();
    Doctor doc = new Doctor("Shao", "Ming", "1");
    Prestacion prestacion = clinica.getPrestaciones().get(0);

    Paciente paciente = new Paciente("Nicolas","Colucci","123954", TipoServicio.PARTICULAR);
    Administrativo admin = new Administrativo("Meze", "Tarado", "123456789");
    Turno turno = clinica.getSobreTurnosDisponiblesPorPrestacion(prestacion).stream().findFirst().get();

    @Test
    public void registrarAsistenciaShouldWork() {

        admin.darTurno(paciente,turno,prestacion,false);
        doc.registrarAsistenciaPaciente(clinica.getListaTurnosDePaciente(paciente),1);
        List<Turno> turnosPaciente = clinica.getListaTurnosDePaciente(paciente);
        assertTrue(turnosPaciente.get(0).getAsistio());
        System.out.println(turno);
    }

    @Test
    public void registrarAtencionDeEstudioShouldWork() {
        Doctor doc = new Doctor("Meze", "Law", "37869123");
        Estudio radiografia = new Estudio("Radiografia");

        Turno turno = new Turno(LocalDateTime.now(), LocalDateTime.now());

        doc.registrarAtencionDeEstudio(turno, radiografia);

        assertEquals(true, radiografia.getEsEstudio());
        assertEquals(true, radiografia.isAsistio());
        assertNotNull(radiografia.getFechaYHoraRealizacion());

    }
}
