package clinica;

import clinica.ubicaciones.Laboratorio;
import individuos.Doctor;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TurnoTests {

    @Test
    public void createATurnoShouldBeAvailable() {
        Turno t = new Turno(LocalDateTime.now(), LocalDateTime.now());
        assertEquals(true, t.getDisponible());
    }

    @Test
    public void createATurnoShouldHavePaymentPending() {
        Turno t = new Turno(LocalDateTime.now(), LocalDateTime.now());
        assertEquals("Impago", t.getTipoDePago());
        assertEquals(false, t.getTurnoPagado());
    }

    @Test
    public void createATurnoShouldHaveAsistioPropertyOnFalse() {
        Turno t = new Turno(LocalDateTime.now(), LocalDateTime.now());
        assertEquals(false, t.getAsistio());
    }

    @Test
    public void toStringMethodShouldnotBeNull(){
        Turno t = new Turno(LocalDateTime.now(), LocalDateTime.now());

        t.setDoctor(new Doctor("Meze","Abogado", "378"));
        t.setUbicacionTurno(new Laboratorio("De Dexter"));
        t.setEspecialidadDelTurno(new Especialidad("Magia negra"));
        String toStringResult = t.toString();

        assertNotNull(toStringResult);
    }
}
