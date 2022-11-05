package individuos;

import clinica.Clinica;
import clinica.prestacion.Prestacion;
import clinica.prestacion.Turno;

import java.util.ArrayList;

public class Administrativo extends Persona{
    /**
     * CONSTRUCTOR
     *
     * @param nombre
     * @param apellido
     * @param dni
     **/
    public Administrativo(String nombre, String apellido, String dni) {
        super(nombre, apellido, dni );
    }

    public void crearPrestacion(String nombre){
        //TODO: Se debe poder armar una prestacion inicial.
        Clinica clinica = Clinica.getInstance();
        Prestacion nuevaPrestacion = new Prestacion(nombre,true);
        clinica.agregarPrestacion(nuevaPrestacion);
    }
    public void darTurno(String dni, Integer nroTurno){
        Clinica clinica = Clinica.getInstance();
        Persona paciente = clinica.getPacientes().stream().filter(p -> p.equals(dni)).findFirst().get();
        Turno turno = clinica.getTurnosDisponibles().remove((int) nroTurno);
        paciente.getTurnos().add(turno);
    }
    public void  prestacionesActivas() {
        //TODO: Se debe poder traer todas las prestaciones activas para luego poder brindar turnos.
        Clinica clinica = Clinica.getInstance();
        if (clinica.getPrestaciones().size() > 0){
            clinica.getPrestaciones().forEach(prestacion -> System.out.println(prestacion.toString()));
        }else{
            System.out.println("No hay prestaciones activas");
        }
    }

    public void especialidadesTurnoDisponibles(){
        //TODO: Se debe poder conocer las especialidades con turnos disponibles
        Clinica clinica = Clinica.getInstance();
        if(clinica.getTurnosDisponibles().size() >0){
            clinica.getTurnosDisponibles().stream().map(t -> t.getEspecialidad()).forEach( t -> System.out.println(t.toString()));
        }else{
            System.out.println("No hay especialidades disponibles ");
        }

    }

    public ArrayList<Turno> turnosTomados(String dni){
        /*
        * Rertona los turnos del paciente con dni *DNI*
        * */
        //TODO: Validaciones.
        Clinica clinica = Clinica.getInstance();
        ArrayList<Turno> turnos = clinica.getPacientes().stream().filter(p -> p.getDni().equals(dni)).findFirst().get().getTurnos();
        return turnos;
    }
    public void asignarUbicacion(Integer nroTurno, Doctor doctor){
        //TODO: Se debe poder asignar ubicaciones (Consultorio/Laboratorio) donde van a brindarse la atencion y un medico
        //Evitar la supersposición de turnos y medicos.
     }

}
