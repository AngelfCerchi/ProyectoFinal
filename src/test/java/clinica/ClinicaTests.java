package clinica;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class ClinicaTests {

    @Test
    public void CreateAClinicShouldReturnANoEmptyInstance(){
        Clinica c = Clinica.getInstance();
        assertNotNull(c);
    }

    @Test
    public void CreateAClinicShouldHaveADirector(){
        Clinica c = new Clinica();
        assertNotNull(c.getDirector().getNombre());
    }

    @Test
    public void CreateAClinicShouldHaveAName(){
        Clinica c = new Clinica();
        assertNotNull(c.getNombre());
        assertEquals("Clinica San Andres Juarez", c.getNombre());
    }

    @Test
    public void CreateAClinicShouldHavePacientesPrestacionesDoctoresTurnosSobreturnosAndLocations(){
        Clinica c = new Clinica();
        assertNotNull(c.getPacientes());
        assertNotNull(c.getPrestaciones());
        assertNotNull(c.getDoctores());
        assertNotNull(c.getTurnos());
        assertNotNull(c.getSobreTurnos());
        assertNotNull(c.getUbicaciones());
    }
}
