package clinica;

import clinica.prestacion.Prestacion;
import clinica.prestacion.PrestacionTradicional;
import individuos.Doctor;
import individuos.Paciente;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class ClinicaTests {

    @Test
    public void createAClinicShouldReturnANoEmptyInstance() {
        Clinica c = Clinica.getInstance();
        assertNotNull(c);
    }

    @Test
    public void createAClinicShouldHaveADirector() {
        Clinica c = new Clinica();
        assertNotNull(c.getDirector().getNombre());
    }

    @Test
    public void createAClinicShouldHaveAName() {
        Clinica c = new Clinica();
        assertNotNull(c.getNombre());
        assertEquals("Clinica San Andres Juarez", c.getNombre());
    }

    @Test
    public void createAClinicShouldHavePacientesPrestacionesDoctoresTurnosSobreturnosAndLocations() {
        Clinica c = new Clinica();
        assertNotNull(c.getPacientes());
        assertNotNull(c.getPrestaciones());
        assertNotNull(c.getDoctores());
        assertNotNull(c.getTurnos());
        assertNotNull(c.getSobreTurnos());
        assertNotNull(c.getUbicaciones());
    }

    @Test
    public void generateReportShouldWork() {
        Clinica c = Clinica.getInstance();

        String reporte = c.reportePrestacionesPorDoctor(c.getDoctores().get(0), false);

        String message = "El doctor seleccionado no tiene prestacion/prestaciones brindadas";

        assertTrue(reporte.contains(message));
    }

    @Test
    public void generateReportWithPrestacionEstudioShouldWork() {
        //Se selecciona arbitrariamente el primer turno disponible de una prestacion estudio
        //y luego se hacen las asignaciones necesarias para probar el reporte de un estudio
        Clinica c = Clinica.getInstance();
        Doctor doctor = c.getDoctores().get(0);
        Prestacion estudioCianuro = c.getPrestaciones().get(2);
        estudioCianuro.setDoctorAsociado(doctor);
        Turno turno = c.getTurnosDisponiblesPorPrestacion(estudioCianuro).get(0);
        turno.setDoctor(doctor);
        turno.setAsistio(true);
        turno.setPrestacionBrindada(estudioCianuro);
        turno.setUbicacionTurno(c.getUbicaciones().get(0));

        String reporte = c.reportePrestacionesPorDoctor(doctor, true);
        assertTrue(reporte.contains("- Analisis Cianuro - Horario: Inicio: 10/11/2022 09:00 - Fin: 10/11/2022 10:00 - Ubicacion: Consultorio 01"));
        assertTrue(reporte.contains("El doctor Coluc Nico brindo en total 1 estudio/estudioss"));
    }

    @Test
    public void generateReportWithPrestacionTradicionalShouldWork() {
        //Se selecciona arbitrariamente el primer turno disponible de una prestacion tradicional
        //y luego se hacen las asignaciones necesarias para probar el reporte de una prestacion tradicional
        Clinica c = Clinica.getInstance();
        Doctor doctor = c.getDoctores().get(0);
        Prestacion prestacionTerapia = c.getPrestaciones().get(3);
        prestacionTerapia.setDoctorAsociado(doctor);
        Turno turno2 = c.getTurnosDisponiblesPorPrestacion(prestacionTerapia).get(0);
        turno2.setDoctor(doctor);
        turno2.setAsistio(true);
        turno2.setPrestacionBrindada(prestacionTerapia);
        turno2.setUbicacionTurno(c.getUbicaciones().get(0));

        String reporte = c.reportePrestacionesPorDoctor(doctor, false);
        assertTrue(reporte.contains("- Terapia - Horario: Inicio: 10/11/2022 09:00 - Fin: 10/11/2022 10:00 - Ubicacion: Consultorio 01"));
        assertTrue(reporte.contains("El doctor Coluc Nico brindo en total 1 prestacion/prestaciones"));
    }

    @Test
    public void agregarPrestacionShouldWork() {
        Clinica c = Clinica.getInstance();
        int cantidadPrestacionesOriginales = c.getPrestaciones().size();

        PrestacionTradicional controlMedico = new PrestacionTradicional("Control medico");
        controlMedico.setEspecialidad(c.getEspecialidades().get(0));
        c.agregarPrestacion(controlMedico);


        assertEquals(cantidadPrestacionesOriginales + 1, c.getPrestaciones().size());
    }

    @Test
    public void getPacientePorDNIShouldReturnResults() {
        Clinica c = Clinica.getInstance();

        c.getPacientes().add(new Paciente("Martin", "Abogado", "37869099", null));

        Paciente p = c.getPacientePorDni("37869099");

        assertNotNull(p);
        assertEquals(p.getDni(), "37869099");
    }

    @Test
    public void getPacientePorDNIShouldReturnNoResults() {
        Clinica c = Clinica.getInstance();

        c.getPacientes().add(new Paciente("Martin", "Abogado", "37869099", null));

        Paciente p = c.getPacientePorDni("123");

        assertNull(p);
    }

    @Test
    public void getPrestacionesPorEspecialidadShouldReturnResults() {

        Clinica c = Clinica.getInstance();
        Especialidad e = new Especialidad("Traumatologia");
        PrestacionTradicional p = new PrestacionTradicional("Control");
        p.setActiva(true);
        e.getPrestaciones().add(p);
        c.getEspecialidades().add(e);
        List<Prestacion> prestaciones = c.getPrestacionesPorEspecialidad(e, true);
        assertEquals(1, prestaciones.size());
    }

    @Test
    public void inhabilitarPrestacionShouldWork() {
        Clinica c = Clinica.getInstance();
        Especialidad e = new Especialidad("Traumatologia");
        PrestacionTradicional p = new PrestacionTradicional("Control");
        p.setActiva(true);

        e.getPrestaciones().add(p);

        c.getEspecialidades().add(e);
        c.getPrestaciones().add(p);

        assertEquals(p.getActiva(), true);

        c.modificarEstadoDeActividadPrestacion(p, false);

        assertEquals(p.getActiva(), false);
    }


    @Test
    public void getListaDeEspecialidadesShouldReturnResults() {

        Clinica c = new Clinica();
        ArrayList<Especialidad> especialidades = c.listaDeEspecialidades(true);
        assertEquals(6, especialidades.size());
    }
}
