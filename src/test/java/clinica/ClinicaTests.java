package clinica;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


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
}
