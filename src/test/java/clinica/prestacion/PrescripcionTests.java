package clinica.prestacion;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PrescripcionTests {

    @Test
    public void agregarEstudioShouldBeWork(){
        Estudio estudio = new Estudio("Orina Completa");
        Prescripcion prescripcion = new Prescripcion();
        prescripcion.agregarEstudio(estudio);
        assertEquals(1,prescripcion.getEstudios().size());
    }

    @Test
    public void agregarMedicamentoShouldBeWork(){
        Medicamento medicamento = new Medicamento("Paracetamol",1);
        Prescripcion prescripcion = new Prescripcion();
        prescripcion.agregarMedicamento(medicamento);
        assertEquals(1,prescripcion.getMedicamentos().size());
    }
}
