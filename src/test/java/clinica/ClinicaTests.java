package clinica;

import clinica.prestacion.Prestacion;
import clinica.prestacion.PrestacionTradicional;
import individuos.Paciente;
import org.junit.Test;

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
    public void generateReportShouldWork(){
        Clinica c = Clinica.getInstance();

        String reporte = c.reportePrestacionesPorDoctor(c.getDoctores().get(0), false);

        String message = "El doctor seleccionado no tiene prestacion/prestaciones brindadas";

        assertTrue(reporte.contains(message));
    }

    @Test
    public void agregarPrestacionShouldWork(){
        Clinica c = Clinica.getInstance();
        int cantidadPrestacionesOriginales = c.getPrestaciones().size();

        PrestacionTradicional controlMedico = new PrestacionTradicional("Control medico");
        controlMedico.setEspecialidad(c.getEspecialidades().get(0));
        c.agregarPrestacion(controlMedico);


        assertEquals(cantidadPrestacionesOriginales+1, c.getPrestaciones().size());
    }

    @Test
    public void getPacientePorDNIShouldReturnResults(){
        Clinica c = Clinica.getInstance();

        c.getPacientes().add(new Paciente("Martin", "Abogado", "37869099", null));

        Paciente p = c.getPacientePorDni("37869099");

        assertNotNull(p);
        assertEquals(p.getDni(), "37869099");
    }

    @Test
    public void getPacientePorDNIShouldReturnNoResults(){
        Clinica c = Clinica.getInstance();

        c.getPacientes().add(new Paciente("Martin", "Abogado", "37869099", null));

        Paciente p = c.getPacientePorDni("123");

        assertNull(p);
    }

    @Test
    public void getPrestacionesPorEspecialidadShouldReturnResults(){

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
    public void inhabilitarPrestacionShouldWork(){
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

}
