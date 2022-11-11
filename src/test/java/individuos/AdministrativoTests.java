package individuos;

import clinica.Clinica;
import clinica.Especialidad;
import clinica.Turno;
import clinica.prestacion.Estudio;
import clinica.prestacion.Prestacion;
import clinica.ubicaciones.Consultorio;
import clinica.ubicaciones.Ubicacion;
import enums.TipoMediosDePago;
import enums.TipoServicio;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdministrativoTests {
    Administrativo administrativo = new Administrativo("Meze", "Tarado", "123456789");
    Paciente paciente = new Paciente("Fabi", "Cerchi", "123654", TipoServicio.PARTICULAR);
    Paciente pacienteDos = new Paciente("Nicolas", "Colucci", "123954", TipoServicio.PARTICULAR);
    Especialidad esp = new Especialidad("Kinesiologia");
    Ubicacion ubi = new Consultorio("Tu casa");
    Prestacion placaToraxica = new Estudio("RX Torax");
    Clinica clinica = Clinica.getInstance();

    @Test
    public void createAPrestacionShouldWork() {
        Doctor doc = new Doctor("Shao", "Ming", "1");
        int cantOriginalDePrestaciones = clinica.getPrestaciones().size();
        administrativo.crearPrestacion("Masajes", false, esp, ubi, doc);
        assertEquals(cantOriginalDePrestaciones + 1, clinica.getPrestaciones().size());
    }

    @Test
    public void darTurnoShouldWork() {
        //Arbitrareamente nos quedamos con el primer turno disponible y prestacion. y se la agregamos a un nuevo paciente.
        Prestacion prestacion = clinica.getPrestaciones().get(0);
        Integer sizeTurnosPaciente = clinica.getListaTurnosDePaciente(paciente).size(); // Esto es 0
        Turno turno = clinica.getTurnosDisponiblesPorPrestacion(prestacion).get(0);
        administrativo.darTurno(paciente, turno, prestacion, false);
        assertEquals(sizeTurnosPaciente + 1, clinica.getListaTurnosDePaciente(paciente).size());
        assertFalse(clinica.getListaTurnosDePaciente(paciente).get(0).getAsistio());
    }

    @Test
    public void darTurnoSobreTurnoShouldWork() {
        //Arbitrareamente nos quedamos con el primer sobre turno disponible y prestacion. y se la agregamos a un nuevo paciente.
        Prestacion prestacion = clinica.getPrestaciones().get(0);
        Integer sizeTurnosPaciente = clinica.getListaTurnosDePaciente(pacienteDos).size(); // Esto es 0
        Turno sobreTurno = clinica.getSobreTurnosDisponiblesPorPrestacion(prestacion).get(0);
        administrativo.darTurno(pacienteDos, sobreTurno, prestacion, true);
        assertEquals(sizeTurnosPaciente + 1, clinica.getListaTurnosDePaciente(pacienteDos).size());
        assertFalse(clinica.getListaTurnosDePaciente(pacienteDos).get(0).getAsistio());
    }

    @Test
    public void crearPacienteShouldWork() {
        int cantidadPacientesPreCreacion = clinica.getPacientes().size();
        Paciente paciente = administrativo.crearPaciente("Gustavo", "Abogado", "45430178", TipoServicio.PREPAGA);

        assertEquals(cantidadPacientesPreCreacion + 1, clinica.getPacientes().size());
        assertTrue(clinica.getPacientes().contains(paciente));
    }

    @Test
    public void crearNuevoDoctorShouldWork() {
        int cantidadDoctoresPreCreacion = clinica.getDoctores().size();
        Doctor nuevoDcotor = administrativo.crearDoctor("Lionel", "Messi", "10101010");

        assertEquals(cantidadDoctoresPreCreacion + 1, clinica.getDoctores().size());
        assertTrue(clinica.getDoctores().contains(nuevoDcotor));
    }

    @Test
    public void pagarTurnoShouldWork() {
        Prestacion prestacion = clinica.getPrestaciones().get(0);
        Turno turno = clinica.getTurnosDisponiblesPorPrestacion(prestacion).get(0);
        String tipoEfectivo = TipoMediosDePago.seleccionarTipoPorIndice(0).getTipo();
        administrativo.pagarTurno(turno, tipoEfectivo);

        assertTrue(turno.getTurnoPagado());
        assertEquals(turno.getTipoDePago(), tipoEfectivo);
    }
}
