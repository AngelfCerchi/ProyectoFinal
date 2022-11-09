package individuos;

import clinica.Especialidad;
import clinica.ubicaciones.Consultorio;
import clinica.ubicaciones.Ubicacion;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AdministrativoTests {

    @Test
    public void createAPrestacionShouldWork(){
        Administrativo a = new Administrativo("Meze", "Law", "123456789");

        Especialidad e = new Especialidad("Kinesiologia");
        Ubicacion u = new Consultorio("Tu casa");

        Doctor doc = new Doctor("Shao", "Ming", "1");
        int cantOriginalDePrestaciones = a.getClinica().getPrestaciones().size();

        a.crearPrestacion("Masajes", false, e, u, doc);
        assertEquals(cantOriginalDePrestaciones+1, a.getClinica().getPrestaciones().size());
    }

    //TODO darTurno test
}
