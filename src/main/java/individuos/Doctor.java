package individuos;

import clinica.Especialidad;
import clinica.Turno;
import clinica.prestacion.Estudio;
import clinica.prestacion.Prescripcion;
import clinica.prestacion.PrestacionTradicional;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends Persona {

    private ArrayList<Especialidad> especialidades = new ArrayList<>();

    /**
     * CONSTRUCTOR
     *
     * @param nombre
     * @param apellido
     * @param dni
     **/
    public Doctor(String nombre, String apellido, String dni) {
        super(nombre, apellido, dni);
    }

    public void registrarAsistenciaPaciente(List<Turno> turnos, int turnoSeleccionado) {
        Turno t = turnos.get(turnoSeleccionado - 1);
        t.setAsistio(true);
        t.setDoctor(this);
    }

    public void registrarAtencionDeEstudio(Turno turno, Estudio estudio) {
        //TODO: Pres
        estudio.setAsistio(true);
        estudio.setFechaYHoraRealizacion(turno.getFin());
        turno.setPrestacionBrindada(estudio);
    }

    public void agregarPrescripcionAPrestacion(Turno turnoAtendido, PrestacionTradicional prestacionTradicional, Prescripcion prescripcion) {
        prestacionTradicional.agregarPrescripcion(prescripcion);
        turnoAtendido.setPrestacionBrindada(prestacionTradicional);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "especialidades=" + especialidades +
                '}';
    }
}
