package clinica;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EspecialidadTests {

    @Test
    public void createAnEspecialidadShouldSetStatusToActive() {
        Especialidad e = new Especialidad("EJEMPLO");
        assertEquals(e.getActiva(), true);
    }

    @Test
    public void createAnEspecialidadShouldHaveNotNullPrestaciones() {
        Especialidad e = new Especialidad("MASAJES");
        assertNotNull(e.getPrestaciones());
    }
}
