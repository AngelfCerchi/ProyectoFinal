package individuos;

import clinica.Clinica;
import clinica.Especialidad;
import clinica.prestacion.Prestacion;
import clinica.Turno;
import clinica.ubicaciones.Ubicacion;

import java.util.ArrayList;
import java.util.List;

public class Administrativo extends Persona {

    private final Clinica clinica = Clinica.getInstance();

    /**
     * CONSTRUCTOR
     *
     * @param nombre
     * @param apellido
     * @param dni
     **/
    public Administrativo(String nombre, String apellido, String dni) {
        super(nombre, apellido, dni);
    }

    public void crearPrestacion(String nombre, Especialidad especialidad) {
        Prestacion nuevaPrestacion = new Prestacion(nombre);
        clinica.agregarPrestacion(nuevaPrestacion, especialidad);
    }

    //TODO si es un solo Doctor por especialidad, para que se lo pasammos aca como parametro?
    //TODO Por la firma del metodo, entiendo que este turno se llamara desde el MENU una vez que se haya pedido data del paciente y la prestacion a pedir turno
    public Turno darTurno(Paciente paciente, Turno turno, Ubicacion ubicacion, Doctor doctor) {

        //Recupero los turnos por la especialidad
        ArrayList<Turno> turnos = clinica.getTurnos().get(turno.getPrestacionBrindada());

        //Agarro uno q este disponible
        Turno turnoDisponible = turnos.stream().filter(t->t.getDisponible()).findFirst().get();
        turnoDisponible.asociarPaciente(paciente);
        turnoDisponible.setUbicacionTurno(ubicacion);
        turnoDisponible.setDisponible(false);

        paciente.agregarTurno(turnoDisponible);

        //TODO verificar que el turno asignado en la linea anterior ya este no-disponible en el turnos del singletone de Clinica

        return turnoDisponible;
    }

    public List<Prestacion> prestacionesActivasPorEspecialidad(Especialidad especialidad) {
        return clinica.getPrestacionesActivasPorEspecialidad(especialidad);
    }

    public List<Turno> turnosDisponiblesPorPrestacion(Prestacion prestacion) {
        return clinica.getTurnosDisponiblesPorPrestacion(prestacion);
    }

    //TODO no debería esto ser por DNI? Porque asi, entiendo que en el menu vamos a tener q buscar por DNI,
    //y recuperarlo para pasarselo a este metodo
    public List<Turno> turnosTomados(Paciente paciente) {
        Paciente pacienteEncontrado = clinica.getPacientes().stream().filter(p->p.getDni().equals(paciente.getDni())).findFirst().get();
        //pacienteEncontrado.
        return pacienteEncontrado.getTurnosAsignados();
    }
}
