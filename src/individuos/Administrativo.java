package individuos;

import clinica.Clinica;
import clinica.Especialidad;
import clinica.Turno;
import clinica.prestacion.Prestacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public Turno darTurno(Paciente paciente, Turno turno) {


        for (Map.Entry<Prestacion, ArrayList<Turno>> entry : clinica.getTurnos().entrySet()) {
            if(entry.getKey().getNombre().equals(turno.getPrestacionBrindada().getNombre())){
                for(Turno t : entry.getValue()){
                    if(t.getHorario().equals(turno.getHorario())){
                        t.asociarPaciente(paciente);
                        t.setDisponible(false);
                        t.setEspecialidadDelTurno(entry.getKey().getEspecialidad());
                        t.setUbicacionTurno(entry.getKey().getUbicacion());
                        t.setDoctor(entry.getKey().getDoctorAsociado());
                        return t;
                    }
                }
            }
        }

        //TODO verificar que el turno asignado en la linea anterior ya este no-disponible en el turnos del singletone de Clinica
        System.out.println("No se encontro el turno o el mismo ya no esta disponible. Saque otro turno.");
        return null;
    }

    public List<Prestacion> prestacionesActivasPorEspecialidad(Especialidad especialidad) {
        return clinica.getPrestacionesActivasPorEspecialidad(especialidad);
    }

    public List<Turno> turnosDisponiblesPorPrestacion(Prestacion prestacion) {
        return clinica.getTurnosDisponiblesPorPrestacion(prestacion);
    }

    //TODO creo que no hace falta esto, se puede consultar en turnos buscando el paciente asociado (evitemos referenciacion en doble sentido)
    //y recuperarlo para pasarselo a este metodo
    /*
    public List<Turno> turnosTomados(Paciente paciente) {
        Paciente pacienteEncontrado = clinica.getPacientes().stream().filter(p -> p.getDni().equals(paciente.getDni())).findFirst().get();
        //pacienteEncontrado.
        return pacienteEncontrado.getTurnosAsignados();
    }
    */
}
