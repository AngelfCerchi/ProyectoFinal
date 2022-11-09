package clinica;

import clinica.prestacion.Prestacion;
import clinica.prestacion.PrestacionTradicional;
import clinica.ubicaciones.Ubicacion;
import individuos.Doctor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class GeneradorDeDatosTests {

    @Test
    public void crearDoctoresEspecialidadesYPrestacionesShouldWork() {

        ArrayList<Doctor> doctores = new ArrayList<>();
        ArrayList<Prestacion> prestaciones = new ArrayList<>();
        ArrayList<Especialidad> especialidades = new ArrayList<>();
        ArrayList<Ubicacion> ubicaciones = new ArrayList<>();

        GeneradorDeDatos.crearDoctoresEspecialidadesYPrestaciones(doctores, prestaciones, especialidades, ubicaciones);

        assertEquals(8, doctores.size());
        assertEquals(8, prestaciones.size());
        assertEquals(6, especialidades.size());
        assertEquals(9, ubicaciones.size());
        assertEquals(doctores.size(), prestaciones.size());
    }

    @Test
    public void crearTurnosYSobreturnos() {

        HashMap<Prestacion, ArrayList<Turno>> turnos = new HashMap<>();
        HashMap<Prestacion, ArrayList<Turno>> sobreTurnos = new HashMap<>();
        Prestacion prestacion = new PrestacionTradicional("CONTROL");

        GeneradorDeDatos.crearTurnosYSobreTurnosParaPrestacion(turnos, sobreTurnos, prestacion);

        assertEquals(1, turnos.size());
        assertEquals(1, sobreTurnos.size());
        assertEquals(8, turnos.get(prestacion).size());
    }

}
