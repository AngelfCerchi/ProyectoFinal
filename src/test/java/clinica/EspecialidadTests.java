package clinica;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EspecialidadTests {

    @Test
    public void CreateAnEspecialidadShouldSetStatusToActive(){
        Especialidad e = new Especialidad("EJEMPLO");
        assertEquals(e.getActiva(), true);
    }

    @Test
    public void CreateAnEspecialidadShouldHaveNotNullPrestaciones(){
        Especialidad e = new Especialidad("MASAJES");
        assertNotNull(e.getPrestaciones());
    }
}
