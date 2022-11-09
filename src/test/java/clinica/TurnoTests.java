package clinica;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

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
}
