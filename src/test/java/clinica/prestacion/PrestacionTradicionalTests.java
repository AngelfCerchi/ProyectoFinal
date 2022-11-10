package clinica.prestacion;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PrestacionTradicionalTests {

    @Test
    public void agregarPrescripcionShouldWork(){
        PrestacionTradicional prestacion = new PrestacionTradicional("Medicina Nuclear");
        Prescripcion prescripcion = new Prescripcion();
        prestacion.agregarPrescripcion(prescripcion);
        assertEquals(1,prestacion.getPrescripciones().size());



    }
}
